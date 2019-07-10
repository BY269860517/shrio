package com.learn.shrio.dao;

import com.learn.shrio.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleMapper {


/**
* @Description: 根据用户id 查找角色集合
* @author: panboyang
* @date :2019-07-10 10:14:53
* @params: No such property: code for class: Script1
 */
    @Select("select ur.role_id as id, " +
            "r.name as name, " +
            "r.description as description " +
            " from  user_role ur left join role r on ur.role_id = r.id " +
            "where  ur.user_id = #{userId}")
    @Results(
            value = {
                    @Result(id=true, property = "id",column = "id"),
                    @Result(property = "name",column = "name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "permissionList",column = "id",
                    many = @Many(select = "com.learn.shrio.dao.PermissionMapper.findPermissionListByRoleId", fetchType = FetchType.DEFAULT)
                    )
            }
    )
    List<Role> findRoleListByUserId(@Param("userId") int userId);


}
