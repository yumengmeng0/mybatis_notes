package mapper;

import domain.User;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/1
 * @version: 1.0.0
 * @description:
 */
public interface UserMapper {
    /**
     * 一对多关联查询：查询所有用户和用户的所有订单
     *
     * @return
     */
    public List<User> findAllWithOrders();

    /**
     * 一对多嵌套查询
     *
     * @return
     */
    public List<User> findAllWithOrders2();


    /**
     * 多对多关联查询：查询所有用户及其所属角色
     *
     * @return
     */
    public List<User> findAllWithRole();

    /**
     * 多对多嵌套查询：查询所有用户及其所属角色
     *
     * @return
     */
    public List<User> findAllWithRole2();

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public User findById(Integer id);
}
