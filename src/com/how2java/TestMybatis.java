package com.how2java;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
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

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        System.out.println("多条件查询");
        Map<String,Object> params = new HashMap<>();
//        params.put("name","a");
        params.put("price","10");
        List<Product> ps2 = session.selectList("listProductCondition",params);
        for (Product p : ps2) {
            System.out.println(p);
        }

        System.out.println("一对多查询");
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c);
            List<Product> ps = c.getProducts();
            for (Product p : ps) {
                System.out.println("\t"+p);
            }
        }

        session.commit();
        session.close();

    }
}