package com.jack.authorizationserver.service;

import com.jack.authorizationserver.mapper.UserMapper;
import com.jack.authorizationserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataUserDetialServies implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.queryUserByName(username);
        if (user == null){
            return null;
        }
        List<String> permissions = userMapper.findPermissionByUserId(user.getId());
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPwd())
                .authorities(permissionArray)
                .build();

        return userDetails;
    }
}
