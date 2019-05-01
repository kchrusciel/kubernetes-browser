package pl.codecouple.kubernetes.browser.presentation.tree;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesFacade;
import pl.codecouple.kubernetes.browser.domain.dto.ContextDto;
import pl.codecouple.kubernetes.browser.domain.dto.NamespaceDto;
import pl.codecouple.kubernetes.browser.domain.dto.PodDto;
import pl.codecouple.kubernetes.browser.presentation.tree.context.KubernetesContextTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.pod.KubernetesPodTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.pod.KubernetesPodTreeRoot;

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
		final KubernetesNamespaceTreeNode namespaceTreeNode = new KubernetesNamespaceTreeNode(namespace.getName());
		namespaceTreeNode.add(createKubernetesPodTreeNodes(namespace));
		return namespaceTreeNode;
	}

	private KubernetesTreeNode createKubernetesPodTreeNodes(final NamespaceDto namespace) {
		final KubernetesPodTreeRoot podTreeRoot = new KubernetesPodTreeRoot();
		for(final PodDto pod : facade.getAllPods(namespace)) {
			podTreeRoot.add(new KubernetesPodTreeNode(pod.getName()));
		}
		return podTreeRoot;
	}
}
