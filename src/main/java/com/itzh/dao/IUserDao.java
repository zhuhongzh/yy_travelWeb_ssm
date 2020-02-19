package com.itzh.dao;

import com.itzh.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *用户接口
 */
@Repository("userDao")
public interface IUserDao {

    //查找所有用户
    @Select("select * from users")
    @Results(id = "userMap",value = {
            @Result(id = true,property = "userId",column = "user_id"),
            @Result(property = "userName",column = "username"),
            @Result(property = "password",column = "user_password"),
            @Result(property = "avatar",column = "user_icon"),
            @Result(property = "email",column = "user_email"),
            @Result(property = "sex",column = "user_sex"),
            @Result(property = "birthday",column = "user_birth"),
            @Result(property = "phone",column = "user_telephone"),
    })
    List<User> findAllUsers();

    //根据电话查询用户
    @Select("select * from users where user_telephone = #{phone}")
    @ResultMap(value = "userMap")
    User findUserByPhone(String phone);

    //根据email查询用户
    @Select("select * from users where user_email = #{email}")
    @ResultMap(value = "userMap")
    User findUserByEamil(String phone);

    @Select("select * from users where user_id = #{id}")
    @ResultMap(value = "userMap")
    User findUserById(int id);


    /**
     * 查找用户收藏的景点
     * @param id
     * @return
     */
    @Select("select * from users where user_id = #{id}")
    @Results(value = {
            @Result(id = true,property = "userId",column = "user_id"),
            @Result(property = "userName",column = "username"),
            @Result(property = "password",column = "user_password"),
            @Result(property = "avatar",column = "user_icon"),
            @Result(property = "email",column = "user_email"),
            @Result(property = "sex",column = "user_sex"),
            @Result(property = "birthday",column = "user_birth"),
            @Result(property = "phone",column = "user_telephone"),
            @Result(property = "sceneryList",column = "user_id",
            many = @Many(select = "com.itzh.dao.sceneryDao.findCollectedSceneryById",
                    fetchType = FetchType.LAZY))
    })
    User findUserCollcetSceneryById(int id);


    //注册保存新用户
    @Insert("insert into users (username,user_password,user_email,user_icon,user_sex,user_birth,user_telephone) values(" +
            "#{userName},#{password},#{email},#{avatar},#{sex},#{birthday},#{phone})")
//    @ResultMap(value = "userMap")
    int saveUser(User user);

    //更新用户信息
    @Update("update users set username=#{userName},user_password=#{password},user_email=#{email},user_icon="+
            "#{avatar},user_sex=#{sex},user_birth=#{birthday},user_telephone=#{phone} where user_telephone=#{phone}")
    int updateUser(User user);


}
