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

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     * @throws UsernameConflictException
     * @throws PasswordNotMatchException
     * @throws UsernameFormartException
     * @throws PasswordFormartException
     */
    User login(String username,String password)throws UsernameConflictException,PasswordNotMatchException, UsernameFormartException,PasswordFormartException;

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws PasswordFormartException
     * @throws UpdateDateException
     */
    void changePassword(Integer id, String oldPassword, String newPassword)
            throws UserNotFoundException, PasswordNotMatchException, PasswordFormartException,UpdateDateException;

    /**
     * 修改信息
     * @param user
     * @throws UpdateDateException
     * @throws UserNotFoundException
     */
    void changeInfo(User user)throws UpdateDateException,UserNotFoundException;

    /**
     * 根据用户名查找
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 修改头像
     * @param id
     * @param avatar
     * @throws UserNotFoundException
     */
    void changeAvatar(Integer id,String avatar) throws UserNotFoundException;
}
