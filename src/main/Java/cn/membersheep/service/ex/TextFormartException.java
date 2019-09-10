package cn.membersheep.service.ex;


public class TextFormartException extends ServiceException{

	
	private static final long serialVersionUID = 4972668347670551239L;

	public TextFormartException() {
		super();
	}

	public TextFormartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TextFormartException(String message, Throwable cause) {
		super(message, cause);
	}

	public TextFormartException(String message) {
		super(message);
	}

	public TextFormartException(Throwable cause) {
		super(cause);
	}
	
}
