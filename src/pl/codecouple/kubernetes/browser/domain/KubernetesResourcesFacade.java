package pl.codecouple.kubernetes.browser.domain;

import java.util.Optional;
import java.util.Set;

import pl.codecouple.kubernetes.browser.domain.dto.ConfigMapDto;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.CronJobDto;
import pl.codecouple.kubernetes.browser.domain.dto.DeploymentDto;
import pl.codecouple.kubernetes.browser.domain.dto.JobDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;
import pl.codecouple.kubernetes.browser.domain.dto.SecretDto;

public class KubernetesResourcesFacade {

	private final KubernetesResourcesProvider kubernetesResourcesProvider;

	public KubernetesResourcesFacade(final KubernetesResourcesProvider kubernetesResourcesProvider) {
		this.kubernetesResourcesProvider = kubernetesResourcesProvider;
	}

	public Set<NamespaceDto> getAllNamespaces() {
		return kubernetesResourcesProvider.getAllNamespaces();
	}

	public Set<ContextDto> getAllContexts() {
		return kubernetesResourcesProvider.getAllContexts();
	}

	public Set<PodDto> getAllPods(final NamespaceDto namespace) {
		return kubernetesResourcesProvider.getAllPods(namespace);
	}

	public Set<SecretDto> getAllSecrets(final NamespaceDto namespace) {
		return kubernetesResourcesProvider.getAllSecrets(namespace);
	}

	public Set<ConfigMapDto> getAllConfigMaps(final NamespaceDto namespace) {
		return kubernetesResourcesProvider.getAllConfigMaps(namespace);
	}

	public Set<DeploymentDto> getAllDeployments(final NamespaceDto namespace) {
		return kubernetesResourcesProvider.getAllDeployments(namespace);
	}

	public Set<JobDto> getAllJobs(final NamespaceDto namespace) {
		return kubernetesResourcesProvider.getAllJobs(namespace);
	}

	public Set<CronJobDto> getAllCronJobs(final NamespaceDto namespace) {
		return kubernetesResourcesProvider.getAllCronJobs(namespace);
	}

	public Optional<String> getNamespaceAsYaml(final String namespace) {
		return kubernetesResourcesProvider.getNamespaceAsYaml(namespace);
	}

}
