package dao.impl;

import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/29
 * @version: 1.0.0
 * @description:
 */
public class UserDaoImpl implements IUserDao {


    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() throws IOException {

        // 1.模版代码重复
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 2.硬编码
        List<User> userList = sqlSession.selectList("userMapper.findAll");

        for (User user : userList) {
            System.out.println("user = " + user);
        }
        return userList;
    }
}
