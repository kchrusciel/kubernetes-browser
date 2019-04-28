package pl.codecouple.kubernetes.browser.presentation;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactoryImpl;

import pl.codecouple.kubernetes.browser.presentation.forms.KubernetesBrowserToolWindowForm;
import pl.codecouple.kubernetes.browser.presentation.icons.KubernetesBrowserPluginIcons;

public class KubernetesBrowserToolWindowFactory implements ToolWindowFactory {

	@Override
	public void createToolWindowContent(@NotNull final Project project, @NotNull final ToolWindow toolWindow) {
		KubernetesBrowserToolWindowForm kubernetesBrowserToolWindowForm = new KubernetesBrowserToolWindowForm(project);
		ContentFactory contentFactory = new ContentFactoryImpl();
		Content content = contentFactory.createContent(kubernetesBrowserToolWindowForm.mainPanel, null, true);
		toolWindow.getContentManager().addContent(content);
		toolWindow.setIcon(KubernetesBrowserPluginIcons.KUBERNETES_LOGO);
	}

}
