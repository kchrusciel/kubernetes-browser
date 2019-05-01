package pl.codecouple.kubernetes.browser.infrastructure.provider.memory;

import java.util.Arrays;
import java.util.List;

import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;

public class InMemoryDataProvider {

	List<NamespaceDto> getAllNamespaces() {
		return Arrays.asList(new NamespaceDto("First"), new NamespaceDto("Second"), new NamespaceDto("Thrid"));
	}

	List<ContextDto> getAllContexts() {
		return Arrays.asList(new ContextDto("Minikube", true), new ContextDto("Second", false), new ContextDto("Thrid", false));
	}

}
