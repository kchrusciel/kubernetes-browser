package pl.codecouple.kubernetes.browser.infrastructure.provider.fabric8io;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesProvider;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;
import pl.codecouple.kubernetes.browser.infrastructure.provider.kubeconfig.KubeConfigFileProvider;

public class KubernetesResourcesFabric8ioProvider implements KubernetesResourcesProvider {

	private final KubernetesClient client;
	private final Config config;

	//TODO KubernetesClientException
	//TODO IOException
	//TODO better config provider
	public KubernetesResourcesFabric8ioProvider() throws IOException {
		this.client = new DefaultKubernetesClient();
		this.config = KubeConfigUtils.parseConfig(KubeConfigFileProvider.findConfigInHomeDir());
	}

	@Override
	public Set<NamespaceDto> getAllNamespaces() {
		return client.namespaces().list().getItems().stream()
				.map(Namespace::getMetadata)
				.map(ObjectMeta::getName)
				.map(NamespaceDto::new)
				.collect(Collectors.toSet());

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
		return client.pods().inNamespace(namespace.getName()).list().getItems().stream()
				.map(Pod::getMetadata)
				.map(ObjectMeta::getName)
				.map(PodDto::new)
				.collect(Collectors.toSet());
	}

}
