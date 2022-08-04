package mapper;

import domain.Orders;
import domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/2
 * @version: 1.0.0
 * @description:
 */
@CacheNamespace // 开启二级缓存
public interface UserMapper {

    @Select(value = "select * from user")
    public List<User> findAll();

    @Insert("insert into user(username, password, birthday) values(#{username}, #{password}, #{birthday})")
    public void save(User user);

    @Update("update user set username = #{username}, password = #{password}, birthday = #{birthday} where id = #{id}")
    public void update(User user);

    @Delete("delete from user where id = #{id}")
    public void delete(Integer id);


    /**
     * 根据id查询客户信息 一对一
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User findById(String id);

    /**
     * 查询所有用户及该用户所有订单 一对多
     *
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "ordersList", javaType = List.class, column = "id",
                    many = @Many(select = "mapper.OrdersMapper.findOrdersByUid", fetchType = FetchType.LAZY))

    })
    public List<User> findAllWithOrders();

    /**
     * 查询所有用户及其对应角色 多对多
     *
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "roleList", javaType = List.class, column = "id",
                    many = @Many(select = "mapper.RoleMapper.findRolesByUid"))
    })
    public List<User> findAllWithRole();
}
