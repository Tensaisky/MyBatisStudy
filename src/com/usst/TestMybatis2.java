package com.usst;

import com.usst.mapper.CategoryMapper;
import com.usst.pojo.Category;
import com.usst.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis2 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

//        add(mapper);
//        delete(mapper);
//        get(mapper);
//        update(mapper);
        listCategoryAndProduct(mapper);

        session.commit();
        session.close();

    }

    private static void update(CategoryMapper mapper) {
        Category c= mapper.get(8);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }

    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(8);
        System.out.println(c.getName());
    }

    private static void delete(CategoryMapper mapper) {
        mapper.delete(4);
        listAll(mapper);
    }

    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }

    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    private static void listCategoryAndProduct(CategoryMapper mapper){
        List<Category> categories = mapper.list();
        for (Category category : categories){
            System.out.println(category.getName());
            List<Product> products = category.getProducts();
            for (Product product : products){
                System.out.println("\t" + product.getName());
            }
        }

    }
}
