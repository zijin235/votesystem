package com.Zijin.mapper;

import com.Zijin.pojo.Vote;

import java.util.List;
import java.util.Map;

public interface VoteDao {
    public int add(Vote vote);
    public List<Vote> findList(Map<String, Object> map);
    public List<Vote> findCollectList(Map<String, Object> map);
    public int getTotal(Map<String, Object> map);
}
