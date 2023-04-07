package com.example.code.mybatis.mapper;

import com.example.code.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * className UserMapper
 * packageName com.example.code.mybatis.mapper
 * description
 *
 * @author CCC
 * @date 2020/12/17 23:00
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUser(int id);

}
