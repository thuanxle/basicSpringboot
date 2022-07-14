package com.kms.baseSpringBoot.batch;

import org.springframework.batch.item.ItemProcessor;

import com.kms.baseSpringBoot.models.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

 @Override
 public User process(User user) throws Exception {
	 return user;
 }

}