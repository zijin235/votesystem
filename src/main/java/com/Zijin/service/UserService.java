package com.Zijin.service;

import com.Zijin.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
	public int add(User user);
	public List<User> findByName(Map<String, Object> map);
	public User findUserByName(String name);
	public int getTotalByName(Map<String, Object> map);
	public int edit(User user);
	public int delete(String ids);
	public User findUserById(Long id);
	public int changePwd(User user);
}
