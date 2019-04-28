package pl.codecouple.kubernetes.browser.presentation.forms;

import javax.swing.JPanel;

import com.intellij.openapi.project.Project;

public class KubernetesBrowserToolWindowForm {

	public JPanel mainPanel;
	private JPanel actionsPanel;
	private JPanel browserPanel;

	private final Project project;

	public KubernetesBrowserToolWindowForm(final Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}
}
