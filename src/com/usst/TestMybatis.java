package com.usst;


import com.usst.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public static void main(String[] args){

        try {
//          根据配置文件得到sqlSessionFactory,由sqlSessionFactory得到session
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

//          最后通过session的selectList方法，调用sql语句listCategory。
//          listCategory这个就是在配置文件Category.xml中那条sql语句设置的id
            List<Category> cs = session.selectList("listCategory");
            for (Category category : cs){
                System.out.println(category.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
