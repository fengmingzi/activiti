package com.main.activity.common.shiro;

import com.main.activity.common.utils.Response;
import com.main.activity.model.Role;
import com.main.activity.model.User;
import com.main.activity.service.RoleService;
import com.main.activity.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: fengguang xu
 * @Description: 实现AuthorizingRealm接口用户用户认证
 * @Date: 2019/4/19 16:01
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = (String) principalCollection.getPrimaryPrincipal();
        User user = new User();
        user.setName(name);
        //查询用户信息
        Response<User> userResponse = userService.getOneByCondition(user);
        if (!userResponse.getIsSuccess()) {
            //查询用户信息失败，需要返回错误，加上异常处理类可以进行抛出
            return null;
        }
        user = userResponse.getData();
        if (user == null) {
            //返回未查询到用户提示
            return null;
        }
        //根据用户查询角色
        Response<List<Role>> roleRes = roleService.getRoleByUid(user.getId());
        if (!roleRes.getIsSuccess()) {
            //查询角色信息失败，需要返回错误
            return null;
        }
        List<Role> roles = roleRes.getData();

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (roles != null) {
            //将角色信息添加到shiro中
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getName());
            }
        }
        //TODO 根据角色查询权限



        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
