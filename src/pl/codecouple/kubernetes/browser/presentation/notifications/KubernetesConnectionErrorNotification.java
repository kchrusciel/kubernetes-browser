package pl.codecouple.kubernetes.browser.presentation.notifications;

import static com.intellij.diagnostic.ReportMessages.GROUP;

import org.jetbrains.annotations.NotNull;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;

public class KubernetesConnectionErrorNotification extends Notification {

	public KubernetesConnectionErrorNotification(@NotNull String content) {
		super(GROUP.getDisplayId(), "", content, NotificationType.ERROR);
	}

	public KubernetesConnectionErrorNotification() {
		super(GROUP.getDisplayId(), "Connection Error", "Cannot establish connection with Kubernetes cluster", NotificationType.ERROR);
	}

}
