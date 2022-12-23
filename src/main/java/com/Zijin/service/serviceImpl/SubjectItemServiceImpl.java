package com.Zijin.service.serviceImpl;

import com.Zijin.mapper.SubjectItemDao;
import com.Zijin.pojo.SubjectItem;
import com.Zijin.service.SubjectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectItemServiceImpl implements SubjectItemService {

	@Autowired
	private SubjectItemDao subjectItemDao;
	@Override
	public int add(SubjectItem subjectItem) {
		return subjectItemDao.add(subjectItem);
	}
	@Override
	public List<SubjectItem> findByName(Map<String, Object> map) {
		return subjectItemDao.findByName(map);
	}
	@Override
	public int getTotalByName(Map<String, Object> map) {
		return subjectItemDao.getTotalByName(map);
	}
	@Override
	public int edit(SubjectItem subjectItem) {
		return subjectItemDao.edit(subjectItem);
	}
	@Override
	public int delete(String ids) {
		return subjectItemDao.delete(ids);
	}
	@Override
	public int addOneVote(Long id) {
		return subjectItemDao.addOneVote(id);
	}

}
