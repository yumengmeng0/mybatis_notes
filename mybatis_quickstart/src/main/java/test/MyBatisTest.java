package test;


import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/29
 * @version: 1.0.0
 * @description:
 */
public class MyBatisTest {

    /**
     * 快速入门测试方法
     */
    @Test
    public void myBatisQuickStart() throws IOException {
        // 1.加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        // 2.获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.执行sql，参数：statementid：namespace.id
        List<Object> objectList = sqlSession.selectList("userMapper.findAll");
        for (Object o : objectList) {
            System.out.println("o ="  + o);
        }

        sqlSession.close();
    }

    /**
     * 测试新增用户
     */
    @Test
    public void testSave() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // 自动提交事务

        User user = new User();
        user.setUsername("张三");
        user.setBirthday(new Date());
        user.setAddress("zz");
        user.setSex("男");

        int insert = sqlSession.insert("userMapper.saveUser", user);
        System.out.println("insert = " + insert);

        // 手动提交事物
//        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testUpdate() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(3);
        user.setUsername("李四");
        int update = sqlSession.update("userMapper.updateUser",user);
        System.out.println("update = " + update);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testDelete() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int delete = sqlSession.delete("userMapper.deleteUser", 3);
        System.out.println("delete =  "+ delete);
        sqlSession.commit();
        sqlSession.close();

    }
}
