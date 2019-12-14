package cn.membersheep.mappers;

import cn.membersheep.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    /**
     * 想用户表数据插入用户信息
     * @param user 用户数据
     * @return 受影响的行数,
     */
    Integer insert(User user);


    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的用户数据
     */
    User findUserByUsername(String username);

    /**
     * 根据id查询用户数据
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * @param id ÓÃ»§id
     * @param password ÓÃ»§ÃÜÂë
     * @return ÐÞ¸ÄµÄÌõÊý
     */
    Integer updatePassword(@Param("id") Integer id,@Param("password") String password);
    Integer updateInfo(User user);
    Integer updateAvatar(@Param("id")Integer id,@Param("avatar")String avatar);

}
