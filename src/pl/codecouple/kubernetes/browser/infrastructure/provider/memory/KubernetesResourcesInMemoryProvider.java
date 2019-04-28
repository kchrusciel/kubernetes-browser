package pl.codecouple.kubernetes.browser.infrastructure.provider.memory;

import java.util.Arrays;
import java.util.List;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesProvider;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;

public class KubernetesResourcesInMemoryProvider implements KubernetesResourcesProvider {

	@Override
	public List<NamespaceDto> getAllNamespaces() {
		return Arrays.asList(new NamespaceDto("First"), new NamespaceDto("Second"), new NamespaceDto("Thrid"));
	}

}
