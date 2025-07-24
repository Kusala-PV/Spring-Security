package com.example.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.UserInfo;

public interface UserInfoRepository extends MongoRepository<UserInfo,String>{
	
	Optional<UserInfo> findByUserName(String userName);

}
