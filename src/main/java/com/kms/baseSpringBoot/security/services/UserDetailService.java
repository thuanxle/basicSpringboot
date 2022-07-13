package com.kms.baseSpringBoot.security.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
	UserDetailService loadUserByUsername(String username) throws UsernameNotFoundException;
}