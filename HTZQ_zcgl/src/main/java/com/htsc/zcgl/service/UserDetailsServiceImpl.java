package com.htsc.zcgl.service;

import com.htsc.zcgl.dao.UserRepository;
import com.htsc.zcgl.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * UserDetailsService的具体实现，用于校验用户和密码
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository myUserRepository;

    // 通过构造器注入MyUserRepository
    public UserDetailsServiceImpl(UserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = myUserRepository.findByUsername(username);
        if(myUser == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), new ArrayList<GrantedAuthority>());
    }
}
