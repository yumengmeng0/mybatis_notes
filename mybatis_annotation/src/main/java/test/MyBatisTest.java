package test;

import domain.Orders;
import domain.User;
import mapper.OrdersMapper;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/2
 * @version: 1.0.0
 * @description:
 */
public class MyBatisTest {

    SqlSession sqlSession = null; // 不要在这里实例化，要考虑作用域和声明周期
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    }

    @After
    public void after() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findAll() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();

        for (User user : userList) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void save() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();

        user.setUsername("王五");
        user.setPassword("88888");
        user.setBirthday(new Date());
        userMapper.save(user);
    }

    @Test
    public void update() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();

        user.setId(3);
        user.setUsername("王五666");
        user.setPassword("88888");
        user.setBirthday(new Date());
        userMapper.update(user);
    }

    @Test
    public void delete() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.delete(3);
    }

    @Test
    public void findAllWithUser() {
        sqlSession = sqlSessionFactory.openSession();
        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> ordersList = ordersMapper.findAllWithUser();

        for (Orders orders : ordersList) {
            System.out.println("orders = " + orders);
        }
    }

    @Test
    public void findAllWithOrders() {
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAllWithOrders();

        for (User user : userList) {
            System.out.println("user = " + user);
        }

    }

    @Test
    public void findAllWithRole() {
        sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAllWithRole();

        for (User user : userList) {
            System.out.println("user = " + user);
        }

    }


}
