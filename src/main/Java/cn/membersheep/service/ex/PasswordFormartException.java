package cn.membersheep.service.ex;

/**
 * �����ʽ�쳣
 * @author tarena
 * @see TextFormartException
 * @see UsernameFormartException
 */
public class PasswordFormartException extends TextFormartException{

	private static final long serialVersionUID = -2705780922814727552L;

	public PasswordFormartException() {
		super();
	}

	public PasswordFormartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordFormartException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordFormartException(String message) {
		super(message);
	}

	public PasswordFormartException(Throwable cause) {
		super(cause);
	}
	
	
}
