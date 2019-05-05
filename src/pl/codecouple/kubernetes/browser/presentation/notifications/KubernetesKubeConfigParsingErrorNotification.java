package pl.codecouple.kubernetes.browser.presentation.notifications;

import static com.intellij.diagnostic.ReportMessages.GROUP;

import org.jetbrains.annotations.NotNull;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;

public class KubernetesKubeConfigParsingErrorNotification extends Notification {

	public KubernetesKubeConfigParsingErrorNotification(@NotNull String content) {
		super(GROUP.getDisplayId(), "", content, NotificationType.ERROR);
	}

	public KubernetesKubeConfigParsingErrorNotification() {
		super(GROUP.getDisplayId(), "Parsing Error", "Cannot parse kubeconfig file", NotificationType.ERROR);
	}

}
