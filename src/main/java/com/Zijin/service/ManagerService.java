package com.Zijin.service;

import com.Zijin.pojo.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerService {
	public Manager findByName(String name);
}
