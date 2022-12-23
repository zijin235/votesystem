package com.Zijin.service.serviceImpl;

import com.Zijin.mapper.VoteDao;
import com.Zijin.pojo.Vote;
import com.Zijin.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteDao voteDao;

	@Override
	public int add(Vote vote) {
		return voteDao.add(vote);
	}

	@Override
	public List<Vote> findList(Map<String, Object> map) {
		return voteDao.findList(map);
	}

	@Override
	public int getTotal(Map<String, Object> map) {
		return voteDao.getTotal(map);
	}

	@Override
	public List<Vote> findCollectList(Map<String, Object> map) {
		return voteDao.findCollectList(map);
	}


}
