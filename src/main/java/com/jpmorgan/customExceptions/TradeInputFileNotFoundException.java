package com.jpmorgan.customExceptions;

/**
 * @author aayushnagar
 * Throw this exception if the sample input file is not present in the resources
 * However this can be customised for an external path exception
 *
 */
public class TradeInputFileNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TradeInputFileNotFoundException(String message) {
		super(message);
	}

	public TradeInputFileNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public TradeInputFileNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
