package pl.codecouple.kubernetes.browser.infrastructure.provider.kubeconfig;

import java.io.File;

public class KubeConfigFileProvider {

	public static final String ENV_HOME = "HOME";
	public static final String KUBEDIR = ".kube";
	public static final String KUBECONFIG = "config";

	private KubeConfigFileProvider() {}

	public static File findConfigInHomeDir() {
		final File homeDir = findHomeDir();
		if(homeDir != null) {
			final File config = new File(new File(homeDir, KUBEDIR), KUBECONFIG);
			if(config.exists()) {
				return config;
			}
		}
		return null;
	}

	private static File findHomeDir() {
		final String envHome = System.getenv(ENV_HOME);
		if(envHome != null && envHome.length() > 0) {
			final File config = new File(envHome);
			if(config.exists()) {
				return config;
			}
		}
		if(System.getProperty("os.name").toLowerCase().startsWith("windows")) {
			String homeDrive = System.getenv("HOMEDRIVE");
			String homePath = System.getenv("HOMEPATH");
			if(homeDrive != null
					&& homeDrive.length() > 0
					&& homePath != null
					&& homePath.length() > 0) {
				File homeDir = new File(new File(homeDrive), homePath);
				if(homeDir.exists()) {
					return homeDir;
				}
			}
			String userProfile = System.getenv("USERPROFILE");
			if(userProfile != null && userProfile.length() > 0) {
				File profileDir = new File(userProfile);
				if(profileDir.exists()) {
					return profileDir;
				}
			}
		}
		return null;
	}

}
