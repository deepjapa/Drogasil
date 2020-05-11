package br.com.drogasil.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 321383678421237887L;

		public BusinessException(String message) {
			super(message);
		}
		
	}
