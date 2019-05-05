package pl.codecouple.kubernetes.browser.presentation.forms;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.intellij.openapi.project.Project;

import pl.codecouple.kubernetes.browser.domain.KubernetesResourcesFacade;

public class KubernetesBrowserToolWindowForm {

	public JPanel mainPanel;
	//TODO add action panel - refresh
	private JPanel actionsPanel;
	private JPanel browserPanel;

	private final Project project;
	private final KubernetesResourcesFacade facade;

	public KubernetesBrowserToolWindowForm(final Project project, final KubernetesResourcesFacade facade) {
		this.project = project;
		this.facade = facade;
		rebuild();
	}

	private void rebuild() {
		KubernetesBrowserForm kubernetesBrowserForm = new KubernetesBrowserForm(facade, project);

		browserPanel.removeAll();
		browserPanel.add(kubernetesBrowserForm.getComponent(), BorderLayout.CENTER);
		browserPanel.revalidate();
		browserPanel.repaint();

	}

	public Project getProject() {
		return project;
	}
}
