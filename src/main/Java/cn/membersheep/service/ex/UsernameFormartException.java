package cn.membersheep.service.ex;

/**
 * �û�����ʽ�쳣
 * @author tarena
 * @see TextFormartException
 * @see PasswordFormartException
 */
public class UsernameFormartException extends TextFormartException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6902602526028523426L;

	public UsernameFormartException() {
		super();
	}

	public UsernameFormartException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UsernameFormartException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameFormartException(String message) {
		super(message);
	}

	public UsernameFormartException(Throwable cause) {
		super(cause);
	}

	
}
