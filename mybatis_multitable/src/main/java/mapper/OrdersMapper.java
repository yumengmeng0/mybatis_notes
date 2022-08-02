package mapper;

import domain.Orders;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/1
 * @version: 1.0.0
 * @description:
 */
public interface OrdersMapper {

    /**
     * 一对一关联查询：查询所有订单，及其所属用户
     * @return
     */
    public List<Orders> findAllWithUser();

    /**
     * 一对一嵌套查询
     * @return
     */
    public List<Orders> findAllWithUser2();

    public List<Orders> findByUid(Integer uid);
}
