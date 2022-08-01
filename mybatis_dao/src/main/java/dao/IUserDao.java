package dao;

import domain.User;

import java.io.IOException;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/29
 * @version: 1.0.0
 * @description:
 */
public interface IUserDao {


    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAll() throws IOException;
}
