package test;

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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithOrders();

        for (User user : userList) {
            System.out.println("user = " + user);

        }

        sqlSession.close();
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
            List<Orders> ordersList = user.getOrdersList();
            System.out.println("ordersList = " + ordersList);
        }

        for (User user : userList) {
            System.out.println("user = " + user);
        }

        sqlSession.close();
    }

    @Test
    public void findByUid() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> list = mapper.findByUid(1);

        for (Orders orders : list) {
            System.out.println("orders = " + orders);
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


    /**
     * 测试一级缓存
     */
    @Test
    public void testOneCache() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);


        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 第一次查询，查询的是数据库
        User user = userMapper.findById(1);
        System.out.println("user = " + user);

//        sqlSession.clearCache();
        //第二次查询，查询的是一级缓存
        User user1 = userMapper.findById(1);
        System.out.println("user1 = " + user1);
        sqlSession.close();
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void tesTwoCache() throws IOException {
        InputStream resource = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        User user = userMapper.findById(1);
        // 只有执行sqlSession.commit()或者sqlSession.close()，一级缓存中的内容才会刷新到二级缓存
        sqlSession.close();

        User user1 = userMapper1.findById(1);


        sqlSession1.close();
    }
}
