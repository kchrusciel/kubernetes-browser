package pl.codecouple.kubernetes.browser.domain;

import java.util.List;

import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;

public interface KubernetesResourcesProvider {

	List<NamespaceDto> getAllNamespaces();

}
