package pl.codecouple.kubernetes.browser.infrastructure.provider.memory;

import java.util.HashSet;
import java.util.Set;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesProvider;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;

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
		return null;
	}

}
