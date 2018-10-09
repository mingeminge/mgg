package com.yzm.sso.service.impl;

import com.yzm.sso.DTO.LoginDTO;
import com.yzm.sso.constant.CookieConstant;
import com.yzm.sso.constant.RedisConstant;
import com.yzm.sso.enums.ResultEnum;
import com.yzm.sso.exception.CustomException;
import com.yzm.sso.mapper.UserMapper;
import com.yzm.sso.model.User;
import com.yzm.sso.model.UserExample;
import com.yzm.sso.service.LoginService;
import com.yzm.sso.utils.CookieUtil;
import com.yzm.sso.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public LoginDTO login(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUNameEqualTo(userName);
        List<User> users = userMapper.selectByExample(example);
        if (null != users && users.size() > 0) {
            LoginDTO loginDTO = new LoginDTO();
            User user = users.get(0);
            if (!password.equals(user.getuPassword())) {
                throw new CustomException(ResultEnum.PASSWORD_ERROR);
            }
            user.setuPassword(null);
            String token = UUID.randomUUID().toString();
            loginDTO.setUser(user);
            loginDTO.setToken(token);
            String value = JsonUtils.objectToJson(user);
            CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
            redisTemplate.opsForValue().set(token, value, RedisConstant.EXPIRE, TimeUnit.SECONDS);
            return loginDTO;
        }
        throw new CustomException(ResultEnum.USER_DOES_NOT_EXIST);
    }

    @Override
    public Boolean logout(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(CookieConstant.TOKEN, null);
        response.addCookie(cookie);
        Boolean delete = redisTemplate.delete(token);
        return delete;
    }

    @Override
    public User getUserByToken(String token) {
        String userStr = redisTemplate.opsForValue().get(token);
        if (!StringUtils.isBlank(userStr)) {
            return JsonUtils.jsonToPojo(userStr, User.class);
        }
        throw new CustomException(ResultEnum.USER_DOES_NOT_EXIST);
    }

    @Override
    public Boolean hasKey(String token) {
        return redisTemplate.hasKey(token);
    }

    @Override
    public void flushExpire(String token) {
        redisTemplate.expire(token, RedisConstant.EXPIRE, TimeUnit.SECONDS);
    }
}
