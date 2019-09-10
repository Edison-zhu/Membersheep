package cn.membersheep.service.ex;

/**
 * 插入数据异常通常在获取持久层操作返回异常时抛出
 */
public class InsertDataException extends ServiceException{

    private static final long serialVersionUID = -8344771768059894761L;

    public InsertDataException() {
    }

    public InsertDataException(String message) {
        super(message);
    }

    public InsertDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertDataException(Throwable cause) {
        super(cause);
    }

    public InsertDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
