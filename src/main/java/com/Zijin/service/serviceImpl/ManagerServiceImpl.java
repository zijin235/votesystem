package com.Zijin.service.serviceImpl;


import com.Zijin.mapper.ManagerDao;
import com.Zijin.pojo.Manager;
import com.Zijin.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Override
	public Manager findByName(String name) {
		return managerDao.findByName(name);
	}

}
