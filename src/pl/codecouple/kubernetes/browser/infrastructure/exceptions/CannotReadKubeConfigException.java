package pl.codecouple.kubernetes.browser.infrastructure.exceptions;

public class CannotReadKubeConfigException extends RuntimeException {

	public CannotReadKubeConfigException(final String message) {
		super(message);
	}
}
