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
     * 一对多嵌套查询
     *
     * @return
     */
    public List<User> findAllWithOrders2();

}
