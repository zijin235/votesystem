package com.Zijin.controller.user;

import com.Zijin.pojo.Subject;
import com.Zijin.pojo.SubjectItem;
import com.Zijin.service.SubjectItemService;
import com.Zijin.service.SubjectService;
import com.Zijin.service.VoteService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/vote_stats")
public class VoteStatsController {

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private SubjectItemService subjectItemService;
	@Autowired
	private VoteService voteService;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", "%%");
		queryMap.put("startIndex", 0);
		queryMap.put("pageSize", 30);
		List<Subject> subjectList = subjectService.findByName(queryMap);
		List<String> subjectNameList = new ArrayList<String>();
		List<Integer> voteNumberList = new ArrayList<Integer>();
		for(Subject subject:subjectList){
			subjectNameList.add(subject.getName());
			voteNumberList.add(subject.getVoteNumber());
		}
		model.addObject("subjectNameList", JSONArray.fromObject(subjectNameList));
		model.addObject("voteNumberList", JSONArray.fromObject(voteNumberList));
		model.setViewName("admin/vote_stats/list");
		return model;
	}

	@RequestMapping(value="/get_subject_stats",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> list(
			@RequestParam(value="subjectName", required=false,defaultValue="") String subjectName
	){
		Map<String,Object> queryMap = new HashMap<String,Object>();
		Map<String,Object> ret = new HashMap<String,Object>();
		queryMap.put("name", "%" + subjectName + "%");
		queryMap.put("startIndex", 0);
		queryMap.put("pageSize", 99);
		List<Subject> findByRelation = subjectService.findByRelation(queryMap);
		if(findByRelation == null || findByRelation.size() == 0){
			ret.put("type", "error");
			ret.put("msg", "?????????????????????????????????");
			return ret;
		}
		Subject subject = findByRelation.get(0);
		List<String> subjectNameList = new ArrayList<String>();
		List<Integer> voteNumberList = new ArrayList<Integer>();
		for(SubjectItem subjectItem:subject.getSubjectItems()){
			subjectNameList.add(subjectItem.getTitle());
			voteNumberList.add(subjectItem.getVoteNumber());
		}
		ret.put("type", "success");
		ret.put("name", subject.getName());
		ret.put("subjectNameList", subjectNameList);
		ret.put("voteNumberList", voteNumberList);
		return ret;
	}

}
