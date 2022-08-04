package domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ymm
 * @date: 2022/8/1
 * @version: 1.0.0
 * @description:
 */
@Data
public class Orders implements Serializable {
    private Integer id;
    private String ordertime;
    private Double total;
    private Integer uid;
    private User user;
}
