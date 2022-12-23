package com.Zijin.mapper;


import com.Zijin.pojo.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SubjectDao {
	public int add(Subject subject);
	public List<Subject> findByName(Map<String, Object> map);
	public int getTotalByName(Map<String, Object> map);
	public int edit(Subject subject);
	public int delete(String ids);
	public List<Subject> findByRelation(Map<String, Object> map);
	public int addOneVote(Long id);
}
