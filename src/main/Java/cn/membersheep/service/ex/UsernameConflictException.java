package cn.membersheep.service.ex;

/**
 * 用户名冲突异常
 */
public class UsernameConflictException extends ServiceException {
    public UsernameConflictException() {
    }

    public UsernameConflictException(String message) {
        super(message);
    }

    public UsernameConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameConflictException(Throwable cause) {
        super(cause);
    }

    public UsernameConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    private static final long serialVersionUID = -3995917052860550590L;
}
