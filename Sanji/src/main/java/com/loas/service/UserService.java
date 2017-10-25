package com.loas.service;

import com.loas.model.User;

public interface UserService {
	public User findUserByEmail(String email);

	public void saveUser(User user);
}
