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

    public List<Orders> findByUid(Integer uid);
}
