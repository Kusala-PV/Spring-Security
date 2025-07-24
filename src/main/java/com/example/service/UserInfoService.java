package com.example.service;

import com.example.dto.UserInfoDto;

public interface UserInfoService {

	public UserInfoDto createUser(UserInfoDto userInfoDto);
	
	public String getUserInfo(UserInfoDto uuserInfoDto);
}
