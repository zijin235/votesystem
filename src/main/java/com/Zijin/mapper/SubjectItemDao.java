package com.Zijin.mapper;

import com.Zijin.pojo.SubjectItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SubjectItemDao {
	public int add(SubjectItem subjectItem);
	public List<SubjectItem> findByName(Map<String, Object> map);
	public int getTotalByName(Map<String, Object> map);
	public int edit(SubjectItem subjectItem);
	public int delete(String ids);
	public int addOneVote(Long id);
}
