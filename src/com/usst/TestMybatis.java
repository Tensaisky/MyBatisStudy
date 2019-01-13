package com.usst;

import com.usst.pojo.Category;
import com.usst.pojo.Order;
import com.usst.pojo.OrderItem;
import com.usst.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
//            增
//            Category category = new Category();
//            category.setName("new category");
//            session.insert("addCategory",category);

//            Product product = new Product();
//            product.setName("product d");
//            product.setPrice(99.99f);
//            product.setCid(1);
//            session.insert("addProduct",product);

//            List<Order> order1s = session.selectList("getOrder",1);
//            Order order1 = order1s.get(0);
//            Product product6 = session.selectOne("getProduct",6);
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(product6);
//            orderItem.setOrder(order1);
//            orderItem.setNumber(200);
//            session.insert("addOrderItem",orderItem);

//            删
//            Category category = new Category();
//            category.setId(1);
//            session.delete("deleteCategory",category);

//            Product product = new Product();
//            product.setId(7);
//            session.delete("deleteProduct",product);

//            List<Order> order1s = session.selectList("getOrder",1);
//            Order order1 = order1s.get(0);
//            Product product6 = session.selectOne("getProduct",6);
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(product6);
//            orderItem.setOrder(order1);
//            session.delete("deleteOrderItem",orderItem);

//            查
//            Category category = session.selectOne("getCategory",3);
//            System.out.println(category.getName());

//            Product product = session.selectOne("getProduct",2);
//            System.out.println(product);

            //choose标签查询
//            Map<String,Object> params = new HashMap<>();
//            params.put("price",90);
//            params.put("name","p");
//            List<Product> products = session.selectList("listProductByChoose",params);
//            for (Product product : products){
//                System.out.println(product);
//            }

            //foreach标签查询
//            List<Integer> ids = new ArrayList<>();
//            ids.add(1);
//            ids.add(3);
//            ids.add(5);
//            List<Product> products = session.selectList("listProductByForeach",ids);
//            for (Product product : products){
//                System.out.println(product);
//            }

//            更
//            Category category = session.selectOne("getCategory",2);
//            category.setName("updateCategory");
//            session.update("updateCategory",category);

//            Product product = session.selectOne("getProduct",5);
//            product.setCid(1);
//            session.update("updateProduct",product);

//            Product p = new Product();
//            p.setId(6);
//            p.setName("product zzz");
//            p.setPrice(99.99f);
//            session.update("updateProductBySet",p);

//            listAll(session);

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
        List<Product> products = session.selectList("listProductAll");
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

    private  static void listCategoryWithProduct(SqlSession session){
        List<Category> categories = session.selectList("listCategoryMap");
        for (Category category : categories){
            System.out.println(category);
            List<Product> products = category.getProducts();
            for (Product product : products){
                System.out.println("\t" + product);
            }
        }
    }

    private static void listProductWithCategory(SqlSession session){
        List<Product> products = session.selectList("listProductMap");
        for (Product product : products){
            System.out.println(product + " 对应的分类为: " + product.getCategory());
        }
    }

    //多对多查询
    private static void listOrder(SqlSession session){
        List<Order> orders = session.selectList("listOrder");
        for (Order order : orders){
            System.out.println(order.getCode());
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems){
                System.out.format("\t%s\t%f\t%d%n",orderItem.getProduct().getName(),
                        orderItem.getProduct().getPrice(),orderItem.getNumber());
            }
        }
    }

}
