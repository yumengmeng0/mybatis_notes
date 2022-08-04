package mapper;

import domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/3
 * @version: 1.0.0
 * @description:
 */
public interface RoleMapper {

    /**
     * 根据uid查询用户所有角色
     *
     * @param uid
     * @return
     */
    @Select("select * from sys_role r inner join sys_user_role ur on r.id = ur.roleid " +
            "where ur.userid = #{uid}")
    public List<Role> findRolesByUid(String uid);
}
