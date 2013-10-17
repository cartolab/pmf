package es.udc.cartolab.gvsig.pmf.layout;


@SuppressWarnings("serial")
public class ExternalError extends RuntimeException {

	public ExternalError(String string, Throwable e) {
		super(string, e);
	}

}
