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

public interface KubernetesResourcesProvider {

	Set<NamespaceDto> getAllNamespaces();

	Set<ContextDto> getAllContexts();

	Set<PodDto> getAllPods(final NamespaceDto namespace);

	Set<SecretDto> getAllSecrets(final NamespaceDto namespace);

	Set<ConfigMapDto> getAllConfigMaps(final NamespaceDto namespace);

	Set<DeploymentDto> getAllDeployments(final NamespaceDto namespace);

	Set<JobDto> getAllJobs(final NamespaceDto namespace);

	Set<CronJobDto> getAllCronJobs(final NamespaceDto namespace);

	Optional<String> getNamespaceAsYaml(final String namespace);

}
