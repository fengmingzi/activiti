package com.main.activity.common.shiro;

import com.main.activity.common.utils.JWTUtil;
import com.main.activity.common.utils.Response;
import com.main.activity.model.Permission;
import com.main.activity.model.Role;
import com.main.activity.model.User;
import com.main.activity.service.PermissionService;
import com.main.activity.service.RoleService;
import com.main.activity.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
    @Autowired
    private PermissionService permissionService;

    /**
     * 引入jwt时，必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * @author fengguang xu
     * @description 接口上有shiro的@RequiresRoles、@RequiresPermissions权限注解时会调用此方法，
     *          会查询出当前用户的角色和权限，进行权限验证
     *          TODO 优化：可以将当前用户的角色和权限信息放到缓存中，避免每次调接口都需要查询数据库
     * @date 2019/4/23 9:50
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //String name = (String) principalCollection.getPrimaryPrincipal();
        String name = JWTUtil.getUsername(principalCollection.toString());
        User user = new User();
        user.setName(name);
        //查询用户信息
        Response<User> userResponse = userService.getOneByCondition(user);
        if (!userResponse.getIsSuccess()) {
            //TODO 查询用户信息失败，需要返回错误，加上异常处理类可以进行抛出
            return null;
        }
        user = userResponse.getData();
        if (user == null) {
            //TODO 返回未查询到用户提示
            return null;
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //根据用户查询角色
        Response<List<Role>> roleRes = roleService.getRoleByUid(user.getId());
        if (!roleRes.getIsSuccess()) {
            //TODO 查询角色信息失败，需要返回错误
            return null;
        }
        List<Role> roles = roleRes.getData();
        //添加角色和权限
        if (roles != null && roles.size() > 0) {
            //将角色信息添加到shiro中
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getName());
            }
        }
        //根据角色查询权限
        Response<List<Permission>> permissionRes = permissionService.getPermissionByUid(user.getId());
        if (!permissionRes.getIsSuccess()) {
            //TODO 查询用户权限失败，返回错误信息
            return null;
        }
        List<Permission> permissions = permissionRes.getData();
        if (permissions != null && permissions.size() > 0) {
            //将权限信息添加到shiro中
            for (Permission permission : permissions) {
                simpleAuthorizationInfo.addStringPermission(permission.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**登陆时会调用此方法，获取验证用户信息*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }

        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null || !JWTUtil.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
            //TODO 异常类增加该异常捕捉
        }

        //获取用户信息
        //String name = authenticationToken.getPrincipal().toString();
        User user = new User();
        user.setName(username);
        Response<User> userResponse = userService.getOneByCondition(user);
        if (!userResponse.getIsSuccess()) {
            //TODO 查询用户失败，这里返回异常信息
            return null;
        }
        User userRes = userResponse.getData();
        if (userRes == null) {
            //TODO 未查询到用户信息，这里返回异常信息
            return null;
        }

        String password = userRes.getPassword();
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        //这里验证authenticationToken和simpleAuthorizationInfo的信息
        //SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, userRes.getPassword(), getName());
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
