package com.example.code.test.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.code.test.module.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonRepository extends BaseMapper<Person> {

//    @Select("SELECT * FROM person WHERE ID = #{id}")
    Person selectOne(@Param("id") Long id);

}
