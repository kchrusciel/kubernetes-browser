package pl.codecouple.kubernetes.browser.presentation.forms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;

import com.intellij.ui.TreeSpeedSearch;
import com.intellij.util.ui.JBUI;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesFacade;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesBrowserTree;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeNodeFactory;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.renderer.KubernetesTreeCellRenderer;
import pl.codecouple.kubernetes.browser.presentation.tree.search.KubernetesBrowserTreeSpeedSearch;

public class KubernetesBrowserForm {

	private JPanel mainPanel;
	private JScrollPane browserScrollPane;

	private final KubernetesResourcesFacade facade;

	KubernetesBrowserForm(final KubernetesResourcesFacade facade) {
		this.facade = facade;
		prepareKubernetesBrowserTree();
	}

	private void prepareKubernetesBrowserTree() {
		KubernetesBrowserTree tree = getTree();
		tree.setRootVisible(false);

		browserScrollPane.setViewportView(tree);
		mainPanel.setBorder(JBUI.Borders.empty());
		ToolTipManager.sharedInstance().registerComponent(tree);
	}

	private KubernetesBrowserTree getTree() {
		final KubernetesBrowserTree tree = new KubernetesBrowserTree(getTreeNode());
		tree.setRootVisible(false);
		tree.setCellRenderer(getTreeCellRenderer());
		setSpeedSearch(tree);
		return tree;
	}

	private KubernetesBrowserTreeSpeedSearch setSpeedSearch(final KubernetesBrowserTree kubernetesBrowserTree) {
		return new KubernetesBrowserTreeSpeedSearch(kubernetesBrowserTree);
	}

	private KubernetesTreeCellRenderer getTreeCellRenderer() {
		KubernetesTreeCellRenderer kubernetesTreeCellRenderer = new KubernetesTreeCellRenderer();

		return kubernetesTreeCellRenderer;
	}

	private KubernetesTreeRoot getTreeNode() {
		return new KubernetesTreeNodeFactory(facade).createKubernetesTreeNodes();
	}

	JPanel getComponent() {
		return mainPanel;
	}

}
