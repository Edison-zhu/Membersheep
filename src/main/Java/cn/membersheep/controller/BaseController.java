package cn.membersheep.controller;

import cn.membersheep.entity.ResponseResult;
import cn.membersheep.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public abstract class BaseController {
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseResult<Void> handleException(Exception e){
        if (e instanceof UsernameConflictException){
            //用户名冲突异常
            return new ResponseResult<Void>(401,e);
        }else if (e instanceof UserNotFoundException){
            //用户名不存在
            return new ResponseResult<Void>(402,e);
        }else if (e instanceof PasswordNotMatchException){
            //密码错误
            return new ResponseResult<Void>(403,e);
        }
        else if (e instanceof InsertDataException){
            //插入数据异常
            return new ResponseResult<Void>(501,e);
        }
        return null;
    }
}
