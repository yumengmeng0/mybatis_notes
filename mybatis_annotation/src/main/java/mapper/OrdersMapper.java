package mapper;

import domain.Orders;
import domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/3
 * @version: 1.0.0
 * @description:
 */
public interface OrdersMapper {


    /**
     * 查询所有订单及其所属用户
     *
     * @return
     */
    @Select("select * from orders")
    @Results({      // 等价于 <resultMap></resultMap>
            @Result(property = "id", column = "id", id = true), // 等价于<id property="id" column="id"></id>
            @Result(property = "ordertime", column = "ordertime"), // 等价于<result property="ordertime" column="ordertime"></result>
            @Result(property = "total", column = "total"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "user", javaType = User.class, column = "uid",
                    one = @One(select = "mapper.UserMapper.findById", fetchType = FetchType.EAGER))
    })
    public List<Orders> findAllWithUser();

    /**
     * 根据用户id查询用户所有订单
     * @param uid
     * @return
     */
    @Select("select * from orders where uid = #{uid}")
    public List<Orders> findOrdersByUid(String uid);

}
