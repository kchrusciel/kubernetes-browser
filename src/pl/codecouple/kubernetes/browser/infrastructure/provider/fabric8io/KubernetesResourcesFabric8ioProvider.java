package pl.codecouple.kubernetes.browser.infrastructure.provider.fabric8io;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intellij.notification.Notifications;
import com.intellij.openapi.diagnostic.Logger;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.NamedContext;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.batch.CronJob;
import io.fabric8.kubernetes.api.model.batch.Job;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.internal.SerializationUtils;
import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesProvider;
import pl.codecouple.kubernetes.browser.domain.dto.ConfigMapDto;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.CronJobDto;
import pl.codecouple.kubernetes.browser.domain.dto.DeploymentDto;
import pl.codecouple.kubernetes.browser.domain.dto.JobDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;
import pl.codecouple.kubernetes.browser.domain.dto.SecretDto;
import pl.codecouple.kubernetes.browser.presentation.notifications.KubernetesConnectionErrorNotification;

public class KubernetesResourcesFabric8ioProvider implements KubernetesResourcesProvider {

	private static final Logger LOG = Logger.getInstance(KubernetesResourcesFabric8ioProvider.class);

	private final KubernetesClient client;
	private final Config config;

	public KubernetesResourcesFabric8ioProvider() {
		this.client = KubernetesClientFabric8ioProvider.getKubernetesClient();
		this.config = KubeConfigFabric8ioProvider.getKubeConfig();
	}

	@Override
	public Set<NamespaceDto> getAllNamespaces() {
		return connectionErrorWrapper(() -> {
			final Set<NamespaceDto> namespaces = new TreeSet<>(Comparator.comparing(NamespaceDto::getName));
			final Optional<NamedContext> currentContext = getCurrentContext();
			client.namespaces().list().getItems().forEach(namespace -> {
				final String name = namespace.getMetadata().getName();
				if(currentContext.isPresent() && currentContext.get().getContext().getNamespace().equalsIgnoreCase(name)) {
					namespaces.add(new NamespaceDto(name, true));
				} else {
					namespaces.add(new NamespaceDto(name, false));
				}
			});

			return namespaces;
		});
	}

	@Override
	public Optional<String> getNamespaceAsYaml(final String namespaceName) {
		return client.namespaces().list().getItems().stream()
				.filter(namespace -> namespace.getMetadata().getName().equalsIgnoreCase(namespaceName))
				.map(this::dumpAsYaml)
				.findAny();
	}

	private String dumpAsYaml(final HasMetadata resourceYoDump) {
		try {
			return SerializationUtils.dumpAsYaml(resourceYoDump);
		} catch(JsonProcessingException e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public Set<ContextDto> getAllContexts() {
		Set<ContextDto> contexts = new TreeSet<>(Comparator.comparing(ContextDto::getName));
		final String currentContext = config.getCurrentContext();
		config.getContexts().forEach(ctx -> {
			if(currentContext.equalsIgnoreCase(ctx.getName())) {
				contexts.add(new ContextDto(ctx.getName(), true));
			} else {
				contexts.add(new ContextDto(ctx.getName(), false));
			}
		});
		return contexts;
	}

	@Override
	public Set<PodDto> getAllPods(final NamespaceDto namespace) {
		return connectionErrorWrapper(() -> client.pods().inNamespace(namespace.getName()).list().getItems().stream()
				.map(Pod::getMetadata)
				.map(ObjectMeta::getName)
				.map(PodDto::new)
				.collect(Collectors.toSet()));
	}

	private Optional<NamedContext> getCurrentContext() {
		final String currentContext = config.getCurrentContext();
		return config.getContexts().stream()
				.filter(ctx -> currentContext.equalsIgnoreCase(ctx.getName()))
				.findAny();
	}

	@Override
	public Set<SecretDto> getAllSecrets(final NamespaceDto namespace) {
		return connectionErrorWrapper(() -> client.secrets().inNamespace(namespace.getName()).list().getItems().stream()
				.map(Secret::getMetadata)
				.map(ObjectMeta::getName)
				.map(SecretDto::new)
				.collect(Collectors.toSet()));
	}

	@Override
	public Set<ConfigMapDto> getAllConfigMaps(final NamespaceDto namespace) {
		return connectionErrorWrapper(() -> client.configMaps().inNamespace(namespace.getName()).list().getItems().stream()
				.map(ConfigMap::getMetadata)
				.map(ObjectMeta::getName)
				.map(ConfigMapDto::new)
				.collect(Collectors.toSet()));
	}

	@Override
	public Set<DeploymentDto> getAllDeployments(final NamespaceDto namespace) {
		return connectionErrorWrapper(() -> client.apps().deployments().inNamespace(namespace.getName()).list().getItems().stream()
				.map(Deployment::getMetadata)
				.map(ObjectMeta::getName)
				.map(DeploymentDto::new)
				.collect(Collectors.toSet()));
	}

	@Override
	public Set<JobDto> getAllJobs(final NamespaceDto namespace) {
		return connectionErrorWrapper(() -> client.batch().jobs().inNamespace(namespace.getName()).list().getItems().stream()
				.map(Job::getMetadata)
				.map(ObjectMeta::getName)
				.map(JobDto::new)
				.collect(Collectors.toSet()));
	}

	@Override
	public Set<CronJobDto> getAllCronJobs(final NamespaceDto namespace) {
		return connectionErrorWrapper(() -> client.batch().cronjobs().inNamespace(namespace.getName()).list().getItems().stream()
				.map(CronJob::getMetadata)
				.map(ObjectMeta::getName)
				.map(CronJobDto::new)
				.collect(Collectors.toSet()));
	}

	private static <T> Set<T> connectionErrorWrapper(final Supplier<Set<T>> supplier) {
		try {
			return supplier.get();
		} catch(KubernetesClientException e) {
			LOG.debug("Cannot establish connection with kubernetes cluster");
			Notifications.Bus.notify(new KubernetesConnectionErrorNotification());
		}
		return Collections.emptySet();
	}

}
