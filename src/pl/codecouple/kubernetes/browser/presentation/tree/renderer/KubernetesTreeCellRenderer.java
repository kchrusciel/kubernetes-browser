package pl.codecouple.kubernetes.browser.presentation.tree.renderer;

import javax.swing.JTree;

import org.jetbrains.annotations.NotNull;

import com.intellij.ui.ColoredTreeCellRenderer;

import pl.codecouple.kubernetes.browser.presentation.icons.KubernetesBrowserPluginIcons;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesSelectableTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.context.KubernetesContextTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.pod.KubernetesPodTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.pod.KubernetesPodTreeRoot;

public class KubernetesTreeCellRenderer extends ColoredTreeCellRenderer {

	@Override
	public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if(value instanceof KubernetesContextTreeNode) {
			customizeKubernetesContextTreeNode((KubernetesContextTreeNode) value);
		}
		if(value instanceof KubernetesNamespaceTreeNode) {
			append(((KubernetesNamespaceTreeNode) value).getUserObject().toString());
		}
		if(value instanceof KubernetesNamespaceTreeRoot) {
			append(((KubernetesNamespaceTreeRoot) value).getUserObject().toString());
		}
		if(value instanceof KubernetesPodTreeNode) {
			append(((KubernetesPodTreeNode) value).getUserObject().toString());
		}
		if(value instanceof KubernetesPodTreeRoot) {
			append(((KubernetesPodTreeRoot) value).getUserObject().toString());
		}
		if(value instanceof KubernetesSelectableTreeNode) {
			customizeKubernetesSelectableTreeNode((KubernetesSelectableTreeNode) value);
		}
	}

	private void customizeKubernetesSelectableTreeNode(final KubernetesSelectableTreeNode selectableTreeNode) {
		final String contextName = selectableTreeNode.getName();
		if(selectableTreeNode.isSelected()) {
			append(String.format("%s %s", KubernetesContextTreeNode.SELECTED_CONTEXT_MARKER, contextName));
		} else {
			append(contextName);
		}
	}

	private void customizeKubernetesContextTreeNode(final KubernetesContextTreeNode treeNode) {
		final String contextName = treeNode.getContextName();
		if(KubernetesContextTreeNode.MINIKUBE_CONTEXT.contains(contextName)) {
			setIcon(KubernetesBrowserPluginIcons.MINIKUBE_LOGO_COLOR);
		} else {
			setIcon(KubernetesBrowserPluginIcons.KUBERNETES_LOGO_COLOR);
		}
	}

}
