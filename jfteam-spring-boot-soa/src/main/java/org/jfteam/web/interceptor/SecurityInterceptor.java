package org.jfteam.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfteam.service.SecurityService;
import org.jfteam.util.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 安全验证拦截器
 * User: fengwenping
 * Date: 2018-07-28
 * Time: 下午10:18
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        final String authorization = httpServletRequest.getHeader("Authorization");
        String reason;
        if (StringUtils.hasText(authorization)) {
            try {
                final String string = new String(Base64.getDecoder().decode(authorization));
                final String[] split = string.split("@");
                if (split.length > 1) {
                    final String appId = split[0];
                    if (StringUtils.hasText(appId)) {
                        final String publicKey = this.securityService.getPublicKey(appId);
                        if (StringUtils.hasText(publicKey)) {
                            final String decrypt = RSAUtils.decryptWithPublicKey(split[1], publicKey);
                            if (Long.parseLong(decrypt) > System.currentTimeMillis()) {
                                return true;
                            }
                            reason = "Authorization已经过期";
                        } else {
                            reason = "Authorization无效";
                        }
                    } else {
                        reason = "appId无效";
                    }
                } else {
                    reason = "Authorization无效,需要包含appId和credential";
                }
            } catch (Exception e) {
                LOGGER.error("Authorization认证失败", e);
                reason = "Authorization认证失败, " + e.getMessage();
            }
        } else {
            reason = "Authorization为空";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", System.currentTimeMillis());
        map.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        map.put("message", reason);
        map.put("path", httpServletRequest.getRequestURI());
        map.put("status", HttpStatus.FORBIDDEN.value());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(json);
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
