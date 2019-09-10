package cn.membersheep.entity;

public class ResponseResult <T>{
    private Integer state = 200;
    private String message;
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state, Exception e) {
        super();
        this.state = state;
        this.message =e.getMessage() ;
        System.out.println("登录成功返回值");
    }

    public ResponseResult(Integer state, String message) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
