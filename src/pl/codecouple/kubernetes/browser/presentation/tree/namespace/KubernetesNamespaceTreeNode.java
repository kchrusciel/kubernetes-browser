package pl.codecouple.kubernetes.browser.presentation.tree.namespace;

import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesSelectableTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeNode;

public class KubernetesNamespaceTreeNode extends KubernetesTreeNode implements KubernetesSelectableTreeNode {

	private final boolean selected;

	public KubernetesNamespaceTreeNode(final Object userObject, final boolean selected) {
		super(userObject);
		this.selected = selected;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public String getName() {
		return getUserObject().toString();
	}
}
