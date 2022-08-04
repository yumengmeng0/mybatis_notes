package domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Date birthday;

    // 订单列表
    private List<Orders> ordersList;

    // 职位列表
    private List<Role> roleList;
}
