package com.Zijin.service.serviceImpl;

import com.Zijin.mapper.UserDao;
import com.Zijin.pojo.User;
import com.Zijin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public List<User> findByName(Map<String, Object> map) {
		return userDao.findByName(map);
	}

	@Override
	public int getTotalByName(Map<String, Object> map) {
		return userDao.getTotalByName(map);
	}

	@Override
	public int edit(User user) {
		return userDao.edit(user);
	}

	@Override
	public int delete(String ids) {
		return userDao.delete(ids);
	}

	@Override
	public User findUserByName(String name) {
		return userDao.findUserByName(name);
	}

	@Override
	public User findUserById(Long id) {
		return userDao.findUserById(id);
	}

	@Override
	public int changePwd(User user) {
		return userDao.changePwd(user);
	}


}
