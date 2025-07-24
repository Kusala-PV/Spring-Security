//package com.example.serviceImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.dto.UserInfoDto;
//import com.example.entity.UserInfo;
//import com.example.mapper.UserInfoMapper;
//import com.example.repository.UserInfoRepository;
//import com.example.service.JWTService;
//import com.example.service.UserInfoService;
//
//@Service
//public class UserInfoServiceImpl implements UserInfoService{
//
//	
//	@Autowired
//	UserInfoRepository userInfoRepository;
//	
//	@Autowired
//	public PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
//	@Autowired
//	public JWTService jwtService;
//	
//	@Override
//	public UserInfoDto createUser(UserInfoDto userInfoDto) {
//		UserInfo userInfo  = UserInfoMapper.toEntity(userInfoDto);
//		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//		userInfoRepository.save(userInfo);
//		return UserInfoMapper.toDto(userInfo);
//	}
//
//	@Override
//	public String getUserInfo(UserInfoDto userInfoDto) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(
//						userInfoDto.userName(),userInfoDto.password()));
//		
//		if(authentication.isAuthenticated()) {
//			return jwtService.generateToken(userInfoDto.userName());
//		}
//		return "failure";
//	}
//
//	
//}


package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.UserInfoDto;
import com.example.entity.UserInfo;
import com.example.mapper.UserInfoMapper;
import com.example.repository.UserInfoRepository;
import com.example.service.JWTService;
import com.example.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public UserInfoServiceImpl(
        UserInfoRepository userInfoRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JWTService jwtService
    ) {
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = UserInfoMapper.toEntity(userInfoDto);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return UserInfoMapper.toDto(userInfo);
    }

    @Override
    public String getUserInfo(UserInfoDto userInfoDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userInfoDto.userName(),
                userInfoDto.password()
            )
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userInfoDto.userName());
        }

        return "failure";
    }
}

