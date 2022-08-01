package test;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.IUserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import mapper.IUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
     * mybatis的dao层传统方法测试
     */
    @Test
    public void findAll() throws IOException {
        //调用持久层方法
        IUserDao userDao = new UserDaoImpl();
        List<User> all = userDao.findAll();

    }

    @Test
    public void findById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 当前返回的其实是基于UserMapper所产生的代理对象：底层JD动态代理。实际类型proxy
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        User user = mapper.findById(1);
        System.out.println("user = " + user);
        sqlSession.close();
    }

    @Test
    public void findAllResultMap() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> userList = mapper.findAllResultMap();

        for (User user : userList) {
            System.out.println("user = " + user);
        }
        sqlSession.close();
    }

    /**
     * 分页
     *
     * @throws IOException
     */
    @Test
    public void findAllResultMapPageHelper() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        // 设置分页参数

        // pageNum 当前页,  pageSize 每页条数
        PageHelper.startPage(1, 3);


        List<User> userList = mapper.findAllResultMap();

        for (User user : userList) {
            System.out.println("user = " + user);
        }

        // 从查询结果计算分页信息
        PageInfo pageInfo = new PageInfo<User>(userList);
        System.out.println("总条数 = " + pageInfo.getTotal());
        System.out.println("总页数 = " + pageInfo.getPages());
        System.out.println("是否是第一页 = " + pageInfo.isIsFirstPage());

        sqlSession.close();
    }


    @Test
    public void findByIdAndUsername1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
//        User user = mapper.findByIdAndUsername1(1, "子慕");
//        User user = mapper.findByIdAndUsername2(1, "子慕");
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("子慕");
        User user = mapper.findByIdAndUsername3(user1);
        System.out.println("user = " + user);

        sqlSession.close();
    }

    @Test
    public void findByUsername() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

//        List<User> userList = mapper.findByUsername1("%子慕%");
        List<User> userList = mapper.findByUsername2("%子慕%");
        for (User user : userList) {
            System.out.println("user = " + user);
        }

        sqlSession.close();
    }


    @Test
    public void saveUser() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        User user1 = new User();
        user1.setUsername("张飞");
        user1.setSex("男");
        user1.setBirthday(new Date());
        user1.setAddress("zz");

        System.out.println("user1 = " + user1);
//        mapper.saveUser1(user1);
        mapper.saveUser2(user1);
        System.out.println("user1 = " + user1);

        sqlSession.close();

    }


    @Test
    public void findByIdAndUsernameIf() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        User user1 = new User();
        user1.setId(5);
        user1.setUsername("张三");
        List<User> userList = mapper.findByIdAndUsernameIf(user1);
        for (User user : userList) {
            System.out.println("user = " + user);
        }
        sqlSession.close();
    }

    @Test
    public void updateIf() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        User user1 = new User();
        user1.setId(1);
        user1.setUsername("张三");
        mapper.updateIf(user1);
        sqlSession.close();
    }

    @Test
    public void findByList() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(6);
        ids.add(8);
        List<User> userList = mapper.findByList(ids);
        for (User user : userList) {
            System.out.println("user = " + user);
        }
        sqlSession.close();
    }

    @Test
    public void findByArray() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        Integer[] ids = {1, 6, 8};

        List<User> userList = mapper.findByArray(ids);
        for (User user : userList) {
            System.out.println("user = " + user);
        }
        sqlSession.close();
    }


}
