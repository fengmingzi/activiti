package com.main.activity.common.filter;

import com.main.activity.common.shiro.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: fengguang xu
 * @Description: JWT过滤器
 * @Date: 2019/4/26 9:47
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {


    /**
     * @author fengguang xu
     * @description 如果带有 token，则对 token 进行检查，否则直接通过
     * @date 2019/4/26 10:43
     * @param request	
     * @param response
     * @param mappedValue
     * @return boolean
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Token");
        if (token != null) {
            try {
                JWTToken jwtToken = new JWTToken(token);
                // 提交给realm进行登入，如果错误他会抛出异常并被捕获
                //TODO 此处可优化，不需要每次都做登陆，例如登陆时将token放入redis，这里直接取出做对比
                getSubject(request, response).login(jwtToken);
                // 如果没有抛出异常则代表登入成功，返回true
                return true;
            } catch (Exception e) {
                log.error("检验token登陆异常：", e);
                throw new ShiroException("权限认证失败");
                //设置编码，否则中文字符在重定向时会变为空字符串
                //String message = URLEncoder.encode(e.getMessage(), "UTF-8");
                //httpServletResponse.sendRedirect("/unauthorized/" + message);
            }
        }

        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        //这里暂时没设游客等情况，所以token不存在直接返回false
        return false;
    }

    /**
     * 对跨域提供支持
     */
    /*@Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }*/

}
