package pl.codecouple.kubernetes.browser.presentation.forms;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileCopyEvent;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileMoveEvent;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.util.ui.JBUI;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesFacade;
import pl.codecouple.kubernetes.browser.presentation.files.KubernetesResourceVirtualFileSystem;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesBrowserTree;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeNodeFactory;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesTreeRoot;
import pl.codecouple.kubernetes.browser.presentation.tree.listener.KubernetesBrowserTreeListener;
import pl.codecouple.kubernetes.browser.presentation.tree.renderer.KubernetesTreeCellRenderer;
import pl.codecouple.kubernetes.browser.presentation.tree.search.KubernetesBrowserTreeSpeedSearch;

public class KubernetesBrowserForm {

	private JPanel mainPanel;
	private JScrollPane browserScrollPane;

	private final KubernetesResourcesFacade facade;
	private final Project project;

	KubernetesBrowserForm(final KubernetesResourcesFacade facade, final Project project) {
		this.facade = facade;
		this.project = project;
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
		// KubernetesClientException
		final KubernetesBrowserTree tree = new KubernetesBrowserTree(getTreeNode());
		tree.setRootVisible(false);
		tree.setCellRenderer(getTreeCellRenderer());
		tree.addMouseListener(getKubernetesBrowserTreeListener(tree));
		setSpeedSearch(tree);
		return tree;
	}

	private KubernetesTreeCellRenderer getTreeCellRenderer() {
		return new KubernetesTreeCellRenderer();
	}

	private KubernetesBrowserTreeListener getKubernetesBrowserTreeListener(final KubernetesBrowserTree tree) {
		return new KubernetesBrowserTreeListener(tree, facade, project);
	}

	private KubernetesBrowserTreeSpeedSearch setSpeedSearch(final KubernetesBrowserTree kubernetesBrowserTree) {
		return new KubernetesBrowserTreeSpeedSearch(kubernetesBrowserTree);
	}

	private KubernetesTreeRoot getTreeNode() {
		return new KubernetesTreeNodeFactory(facade).createKubernetesTreeNodes();
	}

	JPanel getComponent() {
		return mainPanel;
	}

}