package com.learn.shrio.dao;

import com.learn.shrio.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

/**
* @Description: 根据角色id查询对应的权限集合
* @author: panboyang
* @date :2019-07-10 10:03:52
* @params: No such property: code for class: Script1
 */
    @Select("select p.id as id, p.name as name, p.url as url from  role_permission rp " +
            "left join permission p on rp.permission_id=p.id " +
            "where  rp.role_id= #{roleId} ")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);

}
