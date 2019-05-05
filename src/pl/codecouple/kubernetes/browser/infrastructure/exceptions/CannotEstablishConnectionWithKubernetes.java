package pl.codecouple.kubernetes.browser.infrastructure.exceptions;

public class CannotEstablishConnectionWithKubernetes extends RuntimeException {

	public CannotEstablishConnectionWithKubernetes(final String message) {
		super(message);
	}
}
