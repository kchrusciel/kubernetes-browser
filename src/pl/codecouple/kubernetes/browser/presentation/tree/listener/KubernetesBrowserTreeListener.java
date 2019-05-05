package pl.codecouple.kubernetes.browser.presentation.tree.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesFacade;
import pl.codecouple.kubernetes.browser.presentation.files.KubernetesResourceVirtualFile;
import pl.codecouple.kubernetes.browser.presentation.files.KubernetesResourceVirtualFileSystem;
import pl.codecouple.kubernetes.browser.presentation.tree.KubernetesBrowserTree;
import pl.codecouple.kubernetes.browser.presentation.tree.namespace.KubernetesNamespaceTreeNode;

public class KubernetesBrowserTreeListener implements MouseListener {

	private final KubernetesBrowserTree tree;
	private final KubernetesResourcesFacade facade;
	private final Project project;

	public KubernetesBrowserTreeListener(
			final KubernetesBrowserTree tree,
			final KubernetesResourcesFacade facade,
			final Project project) {

		this.tree = tree;
		this.facade = facade;
		this.project = project;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		if(e.getClickCount() == 2) {
			final Object component = tree.getSelectionPath().getLastPathComponent();
			if(component instanceof KubernetesNamespaceTreeNode) {
				//Here editor logic
				final KubernetesNamespaceTreeNode namespaceTreeNode = (KubernetesNamespaceTreeNode) component;
				final String namespaceName = namespaceTreeNode.getName();
				final Optional<String> namespaceAsYaml = facade.getNamespaceAsYaml(namespaceName);
				if(namespaceAsYaml.isPresent()) {
					final KubernetesResourceVirtualFile virtualFile = new KubernetesResourceVirtualFile(namespaceName + ".yaml", KubernetesResourceVirtualFileSystem.getInstance(), namespaceAsYaml.get().getBytes());
					new OpenFileDescriptor(project, virtualFile).navigateInEditor(project,true);
				}
			}
		}
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		//Empty
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		//Empty
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		//Empty
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		//Empty
	}
}
