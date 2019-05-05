package pl.codecouple.kubernetes.browser.infrastructure.provider.fabric8io;

import com.intellij.notification.Notifications;
import com.intellij.openapi.diagnostic.Logger;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import pl.codecouple.kubernetes.browser.infrastructure.exceptions.CannotEstablishConnectionWithKubernetes;
import pl.codecouple.kubernetes.browser.presentation.notifications.KubernetesConnectionErrorNotification;

public class KubernetesClientFabric8ioProvider {

	private static final Logger LOG = Logger.getInstance(KubernetesClientFabric8ioProvider.class);

	private KubernetesClientFabric8ioProvider() {
	}

	public static KubernetesClient getKubernetesClient() {
		try {
			return new DefaultKubernetesClient();
		} catch(KubernetesClientException e) {
			LOG.error("Cannot establish connection with kubernetes cluster");
			Notifications.Bus.notify(new KubernetesConnectionErrorNotification());
			throw new CannotEstablishConnectionWithKubernetes("Cannot establish connection with kubernetes cluster");
		}
	}

}
