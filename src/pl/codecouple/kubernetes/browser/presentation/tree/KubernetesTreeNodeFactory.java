package pl.codecouple.kubernetes.browser.presentation.tree;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesFacade;
import pl.codecouple.kubernetes.browser.domain.dto.ConfigMapDto;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.CronJobDto;
import pl.codecouple.kubernetes.browser.domain.dto.DeploymentDto;
import pl.codecouple.kubernetes.browser.domain.dto.JobDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;
import pl.codecouple.kubernetes.browser.domain.dto.SecretDto;
import pl.codecouple.kubernetes.browser.presentation.tree.configuration.KubernetesConfigurationTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.configuration.configmap.KubernetesConfigMapTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.configuration.configmap.KubernetesConfigMapTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.configuration.secret.KubernetesSecretTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.configuration.secret.KubernetesSecretTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.context.KubernetesContextTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.KubernetesWorkloadTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.cronjob.KubernetesCronJobTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.cronjob.KubernetesCronJobTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.deployment.KubernetesDeploymentTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.deployment.KubernetesDeploymentTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.job.KubernetesJobTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.job.KubernetesJobTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.pod.KubernetesPodTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.workload.pod.KubernetesPodTreeRoot;

public class KubernetesTreeNodeFactory {

	private final KubernetesResourcesFacade facade;

	public KubernetesTreeNodeFactory(final KubernetesResourcesFacade facade) {
		this.facade = facade;
	}

	public KubernetesTreeRoot createKubernetesTreeNodes() {
		KubernetesTreeRoot root = new KubernetesTreeRoot();
		return createKubernetesContextTreeNodes(root);
	}

	private KubernetesTreeRoot createKubernetesContextTreeNodes(final KubernetesTreeRoot root) {
		for(final ContextDto context : facade.getAllContexts()) {
			final KubernetesContextTreeNode contextTreeRoot = new KubernetesContextTreeNode(context.getName(), context.isSelected());
			if(context.isSelected()) {
				contextTreeRoot.add(createKubernetesNamespaceTreeNodes());
			}
			root.add(contextTreeRoot);
		}
		return root;
	}

	private KubernetesTreeNode createKubernetesNamespaceTreeNodes() {
		KubernetesNamespaceTreeRoot namespaceTreeRoot = new KubernetesNamespaceTreeRoot();
		for(final NamespaceDto namespace : facade.getAllNamespaces()) {
			namespaceTreeRoot.add(createKubernetesNamespaceTreeNode(namespace));
		}
		return namespaceTreeRoot;
	}

	private KubernetesTreeNode createKubernetesNamespaceTreeNode(final NamespaceDto namespace) {
		final KubernetesNamespaceTreeNode namespaceTreeNode = new KubernetesNamespaceTreeNode(namespace.getName(), namespace.isSelected());
		namespaceTreeNode.add(createKubernetesWorkloadTreeNode(namespace));
		namespaceTreeNode.add(createKubernetesConfigurationTreeNode(namespace));
		return namespaceTreeNode;
	}

	private KubernetesTreeNode createKubernetesWorkloadTreeNode(final NamespaceDto namespace) {
		final KubernetesWorkloadTreeRoot workloadTreeRoot = new KubernetesWorkloadTreeRoot();
		workloadTreeRoot.add(createKubernetesPodTreeNodes(namespace));
		workloadTreeRoot.add(createKubernetesDeploymentTreeNodes(namespace));
		workloadTreeRoot.add(createKubernetesJobTreeNodes(namespace));
		workloadTreeRoot.add(createKubernetesCronJobTreeNodes(namespace));
		return workloadTreeRoot;
	}

	private KubernetesTreeNode createKubernetesPodTreeNodes(final NamespaceDto namespace) {
		final KubernetesPodTreeRoot podTreeRoot = new KubernetesPodTreeRoot();
		for(final PodDto pod : facade.getAllPods(namespace)) {
			podTreeRoot.add(new KubernetesPodTreeNode(pod.getName()));
		}
		return podTreeRoot;
	}

	private KubernetesTreeNode createKubernetesDeploymentTreeNodes(final NamespaceDto namespace) {
		final KubernetesDeploymentTreeRoot deploymentTreeRoot = new KubernetesDeploymentTreeRoot();
		for(final DeploymentDto deploymentDto : facade.getAllDeployments(namespace)) {
			deploymentTreeRoot.add(new KubernetesDeploymentTreeNode(deploymentDto.getName()));
		}
		return deploymentTreeRoot;
	}

	private KubernetesTreeNode createKubernetesJobTreeNodes(final NamespaceDto namespace) {
		final KubernetesJobTreeRoot jobTreeRoot = new KubernetesJobTreeRoot();
		for(final JobDto jobDto : facade.getAllJobs(namespace)) {
			jobTreeRoot.add(new KubernetesJobTreeNode(jobDto.getName()));
		}
		return jobTreeRoot;
	}

	private KubernetesTreeNode createKubernetesCronJobTreeNodes(final NamespaceDto namespace) {
		final KubernetesCronJobTreeRoot cronJobTreeRoot = new KubernetesCronJobTreeRoot();
		for(final CronJobDto cronJobDto : facade.getAllCronJobs(namespace)) {
			cronJobTreeRoot.add(new KubernetesCronJobTreeNode(cronJobDto.getName()));
		}
		return cronJobTreeRoot;
	}

	private KubernetesTreeNode createKubernetesConfigurationTreeNode(final NamespaceDto namespace) {
		final KubernetesConfigurationTreeRoot configurationTreeRoot = new KubernetesConfigurationTreeRoot();
		configurationTreeRoot.add(createKubernetesSecretTreeNodes(namespace));
		configurationTreeRoot.add(createKubernetesConfigMapTreeNodes(namespace));
		return configurationTreeRoot;
	}

	private KubernetesTreeNode createKubernetesSecretTreeNodes(final NamespaceDto namespace) {
		final KubernetesSecretTreeRoot secretTreeRoot = new KubernetesSecretTreeRoot();
		for(final SecretDto secretDto : facade.getAllSecrets(namespace)) {
			secretTreeRoot.add(new KubernetesSecretTreeNode(secretDto.getName()));
		}
		return secretTreeRoot;
	}

	private KubernetesTreeNode createKubernetesConfigMapTreeNodes(final NamespaceDto namespace) {
		final KubernetesConfigMapTreeRoot configMapTreeRoot = new KubernetesConfigMapTreeRoot();
		for(final ConfigMapDto configMapDto : facade.getAllConfigMaps(namespace)) {
			configMapTreeRoot.add(new KubernetesConfigMapTreeNode(configMapDto.getName()));
		}
		return configMapTreeRoot;
	}

}
