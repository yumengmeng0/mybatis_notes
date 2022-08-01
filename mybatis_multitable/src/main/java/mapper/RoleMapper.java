package mapper;

import domain.Role;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/1
 * @version: 1.0.0
 * @description:
 */
public interface RoleMapper {

    /**
     * 根据用户id查询用户角色
     * @param uid
     * @return
     */
    public List<Role> findByUid(Integer uid);
}
