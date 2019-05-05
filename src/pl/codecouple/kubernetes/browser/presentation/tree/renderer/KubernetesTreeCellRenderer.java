package pl.codecouple.kubernetes.browser.presentation.tree.renderer;

import javax.swing.JTree;

import org.jetbrains.annotations.NotNull;

import com.intellij.ui.ColoredTreeCellRenderer;

import pl.codecouple.kubernetes.browser.presentation.icons.KubernetesBrowserPluginIcons;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesSelectableTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.context.KubernetesContextTreeNode;

public class KubernetesTreeCellRenderer extends ColoredTreeCellRenderer {

	@Override
	public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if(value instanceof KubernetesContextTreeNode) {
			customizeKubernetesContextTreeNode((KubernetesContextTreeNode) value);
		}
		if(value instanceof KubernetesSelectableTreeNode) {
			customizeKubernetesSelectableTreeNode((KubernetesSelectableTreeNode) value);
		}
		if(value instanceof KubernetesTreeNode) {
			customizeKubernetesTreeNode((KubernetesTreeNode) value);
		}
	}

	private void customizeKubernetesTreeNode(final KubernetesTreeNode kubernetesTreeNode) {
		append(kubernetesTreeNode.getUserObject().toString());
	}

	private void customizeKubernetesSelectableTreeNode(final KubernetesSelectableTreeNode selectableTreeNode) {
		if(selectableTreeNode.isSelected()) {
			append(String.format("%s ", KubernetesContextTreeNode.SELECTED_CONTEXT_MARKER));
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
