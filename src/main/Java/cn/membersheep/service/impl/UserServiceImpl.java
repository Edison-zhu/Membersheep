package cn.membersheep.service.impl;

import cn.membersheep.entity.ResponseResult;
import cn.membersheep.entity.User;
import cn.membersheep.mappers.UserMapper;
import cn.membersheep.service.UserService;
import cn.membersheep.service.ex.*;
import cn.membersheep.util.TextValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User reg(User user) throws UsernameConflictException, InsertDataException,UsernameFormartException, PasswordFormartException{
        if (!TextValidator.checkUsername(user.getUsername())){
            throw new UsernameFormartException("尝试登陆的用户名"+user.getUsername()+"格式不正确");
        }
        if (!TextValidator.checkUsername(user.getPassword())){
            throw new PasswordFormartException("尝试登陆的密码格式不正确");
        }
        User data = findUserByname(user.getUsername());
        if (data!=null){
            throw new UsernameConflictException("尝试注册的用户名"+user.getUsername()+"已经被占用");
        }else {
           User result = insert(user);
           return result;
        }
    }

    @Override
    public User login(String username, String password) throws UsernameConflictException, PasswordNotMatchException {
        if (!TextValidator.checkUsername(username)){
            throw new UsernameFormartException("尝试登陆的用户名"+username+"格式不正确");
        }
        if (!TextValidator.checkUsername(password)){
            throw new PasswordFormartException("尝试登陆的密码格式不正确");
        }
        //根据用户名查询
        User user=findUserByname(username);
        if (user!=null){
            String salt = user.getSalt();
            String md5Password = getEncrypytedPassword(password,salt);
            if (user.getPassword().equals(md5Password)){
                return user;
            }else {
                throw new PasswordNotMatchException("密码不正确!");
            }

        }else {
            throw new UserNotFoundException("尝试登陆的用户名"+username+"不存在");
        }
    }
    public void changePassword(Integer id, String oldPassword, String newPassword)
            throws UserNotFoundException, PasswordNotMatchException, PasswordFormartException,UpdateDateException {
        User result = findUserById(id);
        if(!TextValidator.checkPassword(newPassword)){
            throw new PasswordFormartException("ÐÂÃÜÂë¸ñÊ½´íÎó,ÇëÊäÈëÕýÈ·µÄÃÜÂë");
        }
        if (result != null) {
            String salt = result.getSalt();
            String oldMd5Password = getEncrypytedPassword(oldPassword, salt);
            if (oldMd5Password.equals(result.getPassword())) {
                String newMd5Password = getEncrypytedPassword(newPassword, salt);
                updatePassword(id, newMd5Password);
            } else {
                throw new PasswordNotMatchException("ÃÜÂë´íÎó");
            }
        } else {
            throw new UserNotFoundException("³¢ÊÔÐÞ¸ÄµÄÓÃ»§Êý¾Ý²»´æÔÚ");
        }
    }
    public void changeInfo(User user) throws UpdateDateException,UserNotFoundException{
        //ÅÐ¶ÏÓÃ»§idÊÇ·ñ´æÔÚ
        if(user.getId() == null){
            throw new UpdateDateException("更新用户信息错误！");
        }
        User result = findUserById(user.getId());
        //ÅÐ¶ÏÓÃ»§Êý¾ÝÊÇ·ñ´æÔÚÊý¾Ý±íÖÐ
        if(result == null){
            throw new UserNotFoundException("尝试访问用户数据不存在");
        }
        //²¹È«ÐèÒª¸üÐÂµÄÊý¾Ý
        user.setModifieduser(result.getUsername());
        user.setModifiedtime(new Date());
        //Ö´ÐÐ¸üÐÂÓÃ»§ÐÅÏ¢²Ù×÷
        updateInfo(user);
    }

    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    public void changeAvatar(Integer id,String avatar) throws UserNotFoundException{
        User result = findUserById(id);
        if(result == null){
            throw new UserNotFoundException("¸ÃÓÃ»§²»´æÔÚ");
        }
        updateAvatar(id, avatar);
    }


    /**
     *查询已经注册的用户
     * @param username
     * @return
     */
    public User findUserByname(String username){
        return userMapper.findUserByUsername(username);
    }

    /**
     * 插入用户数据
     * @param user
     * @return
     */
    private User insert(User user){
        //生成随机盐值，并封装到user中
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        //取出user中的原密码
        String oldPassword = user.getPassword();
        String md5Password = getEncrypytedPassword(oldPassword,salt);
        user.setPassword(md5Password);
        user.setIsDelete(0);
        //数据库日志
        Date now = new Date();
        user.setCreatetime(now);
        user.setModifiedtime(now);
        user.setCreateuser("朱燕冰");
        user.setModifieduser("朱燕冰");

        Integer rows=userMapper.insert(user);
        if (rows==1){
            return user;
        }else {
            throw new InsertDataException("增加用户数据失败");
        }

    }

    /**
     * 获取加密后的密码
     * @param password
     * @param salt
     * @return
     */
    private String getEncrypytedPassword(String password,String salt){
        String str1= DigestUtils.md5Hex(password).toUpperCase();
        String str2= DigestUtils.md5Hex(password).toUpperCase();
        String str3 = str1+str2;
        String result = "";
        for (int i=0;i<5;i++){
            result = DigestUtils.md5Hex(str3).toUpperCase();
        }
        return result;
    }

    private Integer updatePassword(Integer id, String password) throws UpdateDateException {
        Integer rows = userMapper.updatePassword(id, password);
        if(rows == 1){
            return 1;
        }else{
            throw new UpdateDateException("密码更新出错");
        }
    }
    private Integer updateInfo(User user) throws UpdateDateException{
        Integer rows = userMapper.updateInfo(user);
        if(rows != 1){
            throw new UpdateDateException("¸üÐÂÐÅÏ¢Ê±·¢Éú´íÎó");
        }
        return rows;
    }

    private void updateAvatar(Integer id,String avatar){
        userMapper.updateAvatar(id, avatar);
    }





}
