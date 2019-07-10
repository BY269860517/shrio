package com.learn.shrio.config;

import com.learn.shrio.domain.Permission;
import com.learn.shrio.domain.Role;
import com.learn.shrio.domain.User;
import com.learn.shrio.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: shrio 自定义Realm
 * @author: panboyang
 * @date :2019-07-09 17:38:50
 * @params: No such property: code for class: Script1
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * @Description: 授权调用
     * @author: panboyang
     * @date :2019-07-10 11:33:12
     * @params: No such property: code for class: Script1
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权 doGetAuthorizationInfo");

        User newUser = (User) principals.getPrimaryPrincipal();
        User user = userService.findAllUserInfoByUsername(newUser.getUsername());
        //思路：得到对应的用户角色和权限集合加到SimpleAuthorizationInfo里面
        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();

        List<Role> roleList = user.getRoleList();
        for (Role role : roleList) {
            stringRoleList.add(role.getName());
            List<Permission> permissionList = role.getPermissionList();
            for (Permission p : permissionList) {
                if (p != null) {
                    stringPermissionList.add(p.getName());
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;
    }

    /**
     * @Description: 用户登录的时候调用
     * @author: panboyang
     * @date :2019-07-10 11:33:55ll
     * @params: No such property: code for class: Script1
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证 doGetAuthenticationInfo");
        //从token获取用户信息，token代表用户输入
        String username = (String)token.getPrincipal();
        User user =  userService.findAllUserInfoByUsername(username);
        //取密码
        String pwd = user.getPassword();
        if(StringUtils.isEmpty(pwd)){
            return null;
        }
//user 当用redis插件的时候需要
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());

    }
}
