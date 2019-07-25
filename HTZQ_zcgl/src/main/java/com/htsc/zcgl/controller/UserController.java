package com.htsc.zcgl.controller;

import com.htsc.zcgl.dao.UserRepository;
import com.htsc.zcgl.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * 用户操作
 */
@Api(value = "用户", description = "接口API")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository applicationUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value="获取用户列表")
    @GetMapping("/userList")
    @ResponseBody
    public Map<String, Object> userList(){
        List<User> myUsers = applicationUserRepository.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("users",myUsers);
        return map;
    }
    /**
     * 该方法是注册用户的方法，默认放开访问控制
     * @param user
     */
    @ApiOperation(value="注册用户")
    @PostMapping("/signup")
    public String signUp(@ApiParam(value = "用户实体类", required = true) @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
        return "success";
    }


}
