package domain;

import lombok.Data;

import java.util.Date;

/**
 * @author: ymm
 * @date: 2022/7/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
}
