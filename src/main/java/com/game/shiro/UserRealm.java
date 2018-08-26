package com.game.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 韩钰琦
 * @Date 13:15 ${Date}
 * @Description
 */
public class UserRealm extends AuthorizingRealm {

    //授权 提供当前用户的权限和角色
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if (user != null) {
            List<Authority> authorities = authorityService.queryByUid(user.getId());
            List<String> pers = new ArrayList<>();
            for (Authority authority: authorities) {
                pers.add(authority.getPer());
            }
            info.addStringPermissions(pers);
        }*/
        return info;
    }

    //认证 --- 登录校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        if (token.getUsername().length() > 0) {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
            return info;
        }
        return null;
    }
}
