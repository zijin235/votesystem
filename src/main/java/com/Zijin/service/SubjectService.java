package com.Zijin.service;

import com.Zijin.pojo.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SubjectService {
	public int add(Subject subject);
	public List<Subject> findByName(Map<String, Object> map);
	public int getTotalByName(Map<String, Object> map);
	public int edit(Subject subject);
	public int delete(String ids);
	public List<Subject> findByRelation(Map<String, Object> map);
	public int addOneVote(Long id);
}
