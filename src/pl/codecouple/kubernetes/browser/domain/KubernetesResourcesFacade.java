package pl.codecouple.kubernetes.browser.domain;

import java.util.List;

import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;

public class KubernetesResourcesFacade {

	private final KubernetesResourcesProvider kubernetesResourcesProvider;

	public KubernetesResourcesFacade(final KubernetesResourcesProvider kubernetesResourcesProvider) {
		this.kubernetesResourcesProvider = kubernetesResourcesProvider;
	}

	public List<NamespaceDto> getAllNamespaces() {
		return kubernetesResourcesProvider.getAllNamespaces();
	}

}
