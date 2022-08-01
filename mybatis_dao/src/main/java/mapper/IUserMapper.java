package mapper;

import domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/29
 * @version: 1.0.0
 * @description:
 */
public interface IUserMapper {

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    public User findById(int id);


    /**
     * 查询所有用户
     *
     * @return
     */
    public List<User> findAllResultMap();

    /**
     * 根据id和用户名查询用户（多条件查询方式1）
     *
     * @param id
     * @param username
     * @return
     */
    public User findByIdAndUsername1(int id, String username);


    /**
     * 根据id和用户名查询用户（多条件查询方式2）
     *
     * @param id
     * @param username
     * @return
     */
    public User findByIdAndUsername2(@Param("userid") int id, @Param("username") String username);

    /**
     * 根据id和用户名查询用户（多条件查询方式3）
     *
     * @param user
     * @return
     */
    public User findByIdAndUsername3(User user);


    /**
     * 根据姓名查询用户（模糊查询方式1，实参：‘%username%’）
     *
     * @param username
     * @return
     */
    public List<User> findByUsername1(String username);

    /**
     * 根据姓名查询用户（模糊查询方式2，实参：‘%username%’）
     *
     * @param username
     * @return
     */
    public List<User> findByUsername2(String username);


    /**
     * 添加用户，获取返回主键：方式1
     *
     * @param user
     */
    public void saveUser1(User user);

    /**
     * 添加用户，获取返回主键：方式2
     *
     * @param user
     */
    public void saveUser2(User user);


    /**
     * 动态sql的if标签：多条件查询
     *
     * @param user
     * @return
     */
    public List<User> findByIdAndUsernameIf(User user);

    /**
     * 动态sql的set标签：动态更新
     *
     * @param user
     */
    public void updateIf(User user);

    /**
     * 动态sql的foreach标签：多值查询
     * 根据多个id值查询用户
     *
     * @param ids
     * @return
     */
    public List<User> findByList(List<Integer> ids);


    /**
     * 动态sql的foreach标签：多值查询
     * 根据多个id值查询用户
     *
     * @param ids
     * @return
     */
    public List<User> findByArray(Integer[] ids);


}
