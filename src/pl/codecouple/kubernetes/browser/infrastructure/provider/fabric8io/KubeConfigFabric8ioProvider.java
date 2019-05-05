package pl.codecouple.kubernetes.browser.infrastructure.provider.fabric8io;

import java.io.IOException;

import com.intellij.notification.Notifications;
import com.intellij.openapi.diagnostic.Logger;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import pl.codecouple.kubernetes.browser.infrastructure.exceptions.CannotReadKubeConfigException;
import pl.codecouple.kubernetes.browser.infrastructure.provider.kubeconfig.KubeConfigFileProvider;
import pl.codecouple.kubernetes.browser.presentation.notifications.KubernetesKubeConfigParsingErrorNotification;

public class KubeConfigFabric8ioProvider {

	private static final Logger LOG = Logger.getInstance(KubeConfigFabric8ioProvider.class);

	private KubeConfigFabric8ioProvider() {
	}

	public static Config getKubeConfig() {
		try {
			return KubeConfigUtils.parseConfig(KubeConfigFileProvider.findConfigInHomeDir());
		} catch(IOException e) {
			LOG.error("Cannot parse kubeconfig file", e);
			Notifications.Bus.notify(new KubernetesKubeConfigParsingErrorNotification());
			throw new CannotReadKubeConfigException("Cannot parse kubeconfig file");
		}
	}

}
