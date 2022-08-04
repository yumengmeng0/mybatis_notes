package test;

import domain.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: ymm
 * @date: 2022/8/4
 * @version: 1.0.0
 * @description:
 */
public class CacheTest {


    /**
     * 测试二级缓存
     */
    @Test
    public void cacheTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        User user = userMapper1.findById("1");
        System.out.println("user = " + user);
        // 一级缓存刷新到二级缓存
        sqlSession1.close();
        User user1 = userMapper2.findById("1");
        System.out.println("user1 = " + user1);
        sqlSession2.close();

    }
}
