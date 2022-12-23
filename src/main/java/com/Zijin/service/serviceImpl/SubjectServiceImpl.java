package com.Zijin.service.serviceImpl;


import com.Zijin.mapper.SubjectDao;
import com.Zijin.pojo.Subject;
import com.Zijin.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	@Override
	public int add(Subject subject) {
		return subjectDao.add(subject);
	}
	@Override
	public List<Subject> findByName(Map<String, Object> map) {
		return subjectDao.findByName(map);
	}
	@Override
	public int getTotalByName(Map<String, Object> map) {
		return subjectDao.getTotalByName(map);
	}
	@Override
	public int edit(Subject subject) {
		return subjectDao.edit(subject);
	}
	@Override
	public int delete(String ids) {
		return subjectDao.delete(ids);
	}
	@Override
	public List<Subject> findByRelation(Map<String, Object> map) {
		return subjectDao.findByRelation(map);
	}
	@Override
	public int addOneVote(Long id) {
		return subjectDao.addOneVote(id);
	}

}
