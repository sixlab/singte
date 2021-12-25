package cn.sixlab.minesoft.singte.core.service;

import cn.sixlab.minesoft.singte.core.common.utils.StConst;
import cn.sixlab.minesoft.singte.core.dao.StUserDao;
import cn.sixlab.minesoft.singte.core.models.StUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StUserDetailsService implements UserDetailsService {

    @Autowired
    private StUserDao userMapper;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        StUser stUser = userMapper.selectByUsername(username);
        if (stUser != null) {
            boolean enable = StConst.YES.equals(stUser.getStatus());

            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(stUser.getRole()));

            return new User(username, stUser.getPassword(), enable, true, true, !enable, authorityList);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}