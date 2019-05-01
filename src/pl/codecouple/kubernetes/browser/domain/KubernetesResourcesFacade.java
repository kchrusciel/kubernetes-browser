package pl.codecouple.kubernetes.browser.domain;

import java.util.Set;

import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;

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
}
