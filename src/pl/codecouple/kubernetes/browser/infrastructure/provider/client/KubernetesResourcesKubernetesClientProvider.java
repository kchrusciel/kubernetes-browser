package pl.codecouple.kubernetes.browser.infrastructure.provider.client;

import java.util.Collections;
import java.util.List;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesProvider;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;

public class KubernetesResourcesKubernetesClientProvider implements KubernetesResourcesProvider {

	@Override
	public List<NamespaceDto> getAllNamespaces() {
		return Collections.emptyList();
	}

}
