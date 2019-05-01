package pl.codecouple.kubernetes.browser.presentation.tree.context;

import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesSelectableTreeNode;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeNode;

public class KubernetesContextTreeNode extends KubernetesTreeNode implements KubernetesSelectableTreeNode {

	public static final String MINIKUBE_CONTEXT = "minikube";
	public static final String SELECTED_CONTEXT_MARKER = "*";

	private final boolean selected;

	public KubernetesContextTreeNode(final Object userObject, final boolean selected) {
		super(userObject);
		this.selected = selected;
	}

	public String getContextName() {
		return userObject.toString().toLowerCase().trim();
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public String getName() {
		return getContextName();
	}
}
