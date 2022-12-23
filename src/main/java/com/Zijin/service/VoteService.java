package com.Zijin.service;

import com.Zijin.pojo.Vote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface VoteService {
	public int add(Vote vote);
	public List<Vote> findList(Map<String, Object> map);
	public List<Vote> findCollectList(Map<String, Object> map);
	public int getTotal(Map<String, Object> map);
}
