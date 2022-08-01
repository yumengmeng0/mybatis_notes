package test;

import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import domain.Orders;
import domain.User;
import mapper.OrdersMapper;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/1
 * @version: 1.0.0
 * @description:
 */
public class MybatisTest {


    @Test
    public void findAllWithUser() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
            List<Orders> ordersList = mapper.findAllWithUser();
            for (Orders orders : ordersList) {
                System.out.println("orders = " + orders);
            }

        }
    }

    @Test
    public void findAllWithUser2() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
            List<Orders> ordersList = mapper.findAllWithUser2();
            for (Orders orders : ordersList) {
                System.out.println("orders = " + orders);
            }

        }
    }

    @Test
    public void findAllWithOrders() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.findAllWithOrders();

            for (User user : userList) {
                System.out.println("user = " + user);
            }

        }
    }

    @Test
    public void findAllWithOrders2() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithOrders2();

        for (User user : userList) {
            System.out.println("user = " + user);
        }

        sqlSession.close();
    }

    @Test
    public void findAllWithRole() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.findAllWithRole();

            for (User user : userList) {
                System.out.println("user = " + user);
            }

        }
    }

    @Test
    public void findAllWithRole2() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.findAllWithRole2();

            for (User user : userList) {
                System.out.println("user = " + user);
            }

        }
    }
}
