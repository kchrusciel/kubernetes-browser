package pl.codecouple.kubernetes.browser.presentation.tree;

import javax.swing.tree.TreeNode;

import com.intellij.openapi.Disposable;
import com.intellij.ui.treeStructure.Tree;

public class KubernetesBrowserTree extends Tree implements Disposable {

	public KubernetesBrowserTree(final TreeNode root) {
		super(root);
	}

	@Override
	public void dispose() {

	}
}
