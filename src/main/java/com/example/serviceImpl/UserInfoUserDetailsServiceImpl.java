//package com.example.serviceImpl;
//
//
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.entity.UserInfo;
//import com.example.mapper.UserInfoUserDetailsMapper;
//import com.example.repository.UserInfoRepository;
//
//@Service
//public class UserInfoUserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private UserInfoRepository userInfoRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserInfo> userInfo = userInfoRepository.findByUserName(username);
//		return userInfo.map(UserInfoUserDetailsMapper::new)
//				.orElseThrow(() -> new UserNameNotFoundException("User"+ username+ "Not found"));
//	}
//
//}


package com.example.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.UserInfo;
import com.example.mapper.UserInfoUserDetailsMapper;
import com.example.repository.UserInfoRepository;

@Service
public class UserInfoUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserName(username);

        return userInfo
                .map(UserInfoUserDetailsMapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}
