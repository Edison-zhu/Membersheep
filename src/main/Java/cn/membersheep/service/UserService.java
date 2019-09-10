package cn.membersheep.service;

import cn.membersheep.entity.User;
import cn.membersheep.service.ex.*;
import com.mysql.cj.exceptions.PasswordExpiredException;

public interface UserService {
    /**
     * 用户注册
     * @param user 返回成功的用户数据
     * @return
     * @throws UsernameConflictException 用户名被占用
     */
    User reg(User user)throws UsernameConflictException, InsertDataException;
    User login(String username,String password)throws UsernameConflictException,PasswordNotMatchException, UsernameFormartException,PasswordFormartException;
    void changePassword(Integer id, String oldPassword, String newPassword)
            throws UserNotFoundException, PasswordNotMatchException, PasswordFormartException,UpdateDateException;
    void changeInfo(User user)throws UpdateDateException,UserNotFoundException;
    User findUserById(Integer id);
    void changeAvatar(Integer id,String avatar) throws UserNotFoundException;
}
