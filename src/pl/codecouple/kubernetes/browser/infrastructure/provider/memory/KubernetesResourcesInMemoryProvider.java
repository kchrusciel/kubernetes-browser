package pl.codecouple.kubernetes.browser.infrastructure.provider.memory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesProvider;
import pl.codecouple.kubernetes.browser.domain.dto.ConfigMapDto;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.CronJobDto;
import pl.codecouple.kubernetes.browser.domain.dto.DeploymentDto;
import pl.codecouple.kubernetes.browser.domain.dto.JobDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;
import pl.codecouple.kubernetes.browser.domain.dto.SecretDto;

public class KubernetesResourcesInMemoryProvider implements KubernetesResourcesProvider {

	private final InMemoryDataProvider memoryDataProvider;

	public KubernetesResourcesInMemoryProvider(final InMemoryDataProvider memoryDataProvider) {
		this.memoryDataProvider = memoryDataProvider;
	}

	@Override
	public Set<NamespaceDto> getAllNamespaces() {
		return new HashSet<>(memoryDataProvider.getAllNamespaces());
	}

	@Override
	public Set<ContextDto> getAllContexts() {
		return new HashSet<>(memoryDataProvider.getAllContexts());
	}

	@Override
	public Set<PodDto> getAllPods(final NamespaceDto namespace) {
		return Collections.emptySet();
	}

	@Override
	public Set<SecretDto> getAllSecrets(final NamespaceDto namespace) {
		return Collections.emptySet();
	}

	@Override
	public Set<ConfigMapDto> getAllConfigMaps(final NamespaceDto namespace) {
		return Collections.emptySet();
	}

	@Override
	public Set<DeploymentDto> getAllDeployments(final NamespaceDto namespace) {
		return Collections.emptySet();
	}

	@Override
	public Set<JobDto> getAllJobs(final NamespaceDto namespace) {
		return Collections.emptySet();
	}

	@Override
	public Set<CronJobDto> getAllCronJobs(final NamespaceDto namespace) {
		return Collections.emptySet();
	}

	@Override
	public Optional<String> getNamespaceAsYaml(final String namespace) {
		return Optional.empty();
	}

}
