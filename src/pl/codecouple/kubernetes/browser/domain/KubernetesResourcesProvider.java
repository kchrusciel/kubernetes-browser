package pl.codecouple.kubernetes.browser.domain;

import java.util.Set;

import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;

public interface KubernetesResourcesProvider {

	Set<NamespaceDto> getAllNamespaces();

	Set<ContextDto> getAllContexts();

	Set<PodDto> getAllPods(final NamespaceDto namespace);

}
