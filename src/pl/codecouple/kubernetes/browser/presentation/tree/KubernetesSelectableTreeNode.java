package pl.codecouple.kubernetes.browser.presentation.tree;

public interface KubernetesSelectableTreeNode {

	String SELECTED_CONTEXT_MARKER = "*";

	boolean isSelected();
	String getName();

}
