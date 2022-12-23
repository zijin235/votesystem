package com.Zijin.mapper;

import com.Zijin.pojo.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao {
	public Manager findByName(String name);
}
