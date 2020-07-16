package com.MySiteTest.dao;

import com.MySiteTest.pojo.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2020/6/25
 */
@Repository
@Mapper
public interface TypeDao {
    @Insert("insert into type values(#{type_name})")
    public void saveType(Type type);
    @Select("select * from type")
    public List<Type> selectAllTypes();
    @Select("select * from type where type_name = #{type_name}")
    public Type selectByTypeName(Type type);
    @Delete("delete from type where type_name = #{type_name}")
    public void deleteType(Type type);
}
