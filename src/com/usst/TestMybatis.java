package com.usst;


import com.usst.pojo.Category;
import com.usst.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    public static void main(String[] args){
        try {
//          根据配置文件得到sqlSessionFactory,由sqlSessionFactory得到session
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

//            Category category = new Category();
//            category.setName("new category");
//            session.insert("addCategory",category);

//            Category category = new Category();
//            category.setId(1);
//            session.delete("deleteCategory",category);

//            Category category = session.selectOne("getCategory",3);
//            System.out.println(category.getName());

//            Category category = session.selectOne("getCategory",2);
//            category.setName("updateCategory");
//            session.update("updateCategory",category);

            listAllByIdAndName(session);

            session.commit();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listAll(SqlSession session){
        //最后通过session的selectList方法，调用sql语句listCategory。
        //listCategory这个就是在配置文件Category.xml中那条sql语句设置的id
        List<Category> cs = session.selectList("listCategory");
        List<Product> products = session.selectList("listProduct");
        for (Category category : cs){
            System.out.println("id:" + category.getId() + " name:" + category.getName());
        }
        for (Product product : products ){
            System.out.println("id:" + product.getId() + " name:" + product.getName()
                    + " price:" + product.getPrice());
        }
    }

    private static void listAllByName(SqlSession session){
        List<Category> categories = session.selectList("listCategoryByName","cat");
        for (Category category : categories){
            System.out.println("id:" + category.getId() + " name:" + category.getName());
        }
    }

    private static void listAllByIdAndName(SqlSession session){
        //selectList方法只能接受一个参数对象，多个参数需要放在Map里，传入map对象
        Map<String,Object> params = new HashMap<>();
        params.put("id",1);
        params.put("name","cat");
        List<Category> categories = session.selectList("listCategoryByIdAndName",params);
        for (Category category : categories){
            System.out.println("id:" + category.getId() + " name:" + category.getName());
        }
    }
}
