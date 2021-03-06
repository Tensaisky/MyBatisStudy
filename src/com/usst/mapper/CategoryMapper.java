package com.usst.mapper;

import com.usst.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface  CategoryMapper {
    @Insert(" insert into category_ ( name ) values (#{name}) ")
    int add(Category category);

    @Delete(" delete from category_ where id = #{id}")
    void delete(int id);

    @Select(" select * from category_ where id = #{id}")
    Category get(int id);

    @Update(" update category_ set name = #{name} where id=#{id}")
    int update(Category category);

    @Select(" select * from category_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "products", javaType = List.class, column = "id",
            many = @Many(select = "com.usst.mapper.ProductMapper.listByCategory"))
    })
    public  List<Category> list();
}
