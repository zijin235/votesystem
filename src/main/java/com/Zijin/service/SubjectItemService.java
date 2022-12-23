package com.Zijin.service;

import com.Zijin.pojo.SubjectItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SubjectItemService {
	public int add(SubjectItem subjectItem);
	public List<SubjectItem> findByName(Map<String, Object> map);
	public int getTotalByName(Map<String, Object> map);
	public int edit(SubjectItem subjectItem);
	public int delete(String ids);
	public int addOneVote(Long id);
}
