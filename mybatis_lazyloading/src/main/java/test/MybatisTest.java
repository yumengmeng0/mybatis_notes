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

}
