package com.Zijin.controller.home;

import com.Zijin.pojo.User;
import com.Zijin.service.UserService;
import com.Zijin.util.CpachaUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService;


	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("home/index");
		return model;
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("home/login");
		return model;
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(ModelAndView model){
		model.setViewName("home/register");
		return model;
	}

	@RequestMapping(value="/change_pwd",method=RequestMethod.GET)
	public ModelAndView changePassword(Long id,ModelAndView model){
		model.addObject("user", userService.findUserById(id));
		model.setViewName("home/change_pwd");
		return model;
	}

	@RequestMapping(value="/login_out",method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request){
		request.getSession().setAttribute("user", null);
		return "redirect:/";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> loginAct(@RequestParam(value="username", required=true) String username,
									   @RequestParam(value="password", required=true) String password,
									   HttpServletRequest request){
		Map<String,String> ret = new HashMap<String,String>();
		if(StringUtils.isEmpty(username)){
			ret.put("type", "error");
			ret.put("msg", "????????????????????????");
			return ret;
		}
		if(StringUtils.isEmpty(password)){
			ret.put("type", "error");
			ret.put("msg", "?????????????????????");
			return ret;
		}

		User user = userService.findUserByName(username);
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "?????????????????????");
			return ret;
		}
		if(!password.equals(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "???????????????");
			return ret;
		}
		if(user.getStatus() != 1){
			ret.put("type", "error");
			ret.put("msg", "???????????????????????????????????????");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "????????????");
		//?????????????????????session
		request.getSession().setAttribute("user",user);
		return ret;
	}

	@RequestMapping(value="/reset_pwd",method=RequestMethod.POST)
	@ResponseBody
	public String resetPwd(String newPwd,Long id){
		User user = userService.findUserById(id);
		if(user == null)return "no";
		if(StringUtils.isEmpty(newPwd))return "no";
		user.setPassword(newPwd);
		if(userService.changePwd(user) <= 0)return "no";
		return "t";
	}

	@RequestMapping(value="/validateLoginUser",method=RequestMethod.POST)
	@ResponseBody
	public String validateLoginUser(String name){
		User user = userService.findUserByName(name);
		if(user == null)return "n";
		return "yes";
	}

	@RequestMapping(value="/validateRegisterUser",method=RequestMethod.POST)
	@ResponseBody
	public String validateRegisterUser(String name){
		User user = userService.findUserByName(name);
		if(user == null)return "n";
		return "f";
	}

	@RequestMapping(value="/user_reg",method=RequestMethod.POST)
	@ResponseBody
	public String userRegister(User user){
		user.setCreateTime(new Date(System.currentTimeMillis()));
		if(userService.add(user) > 0){
			return "t";
		}
		return "f";
	}

//	//?????????
//	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
//	public void getCpacha(@RequestParam(value="type", required=false,defaultValue="adminLogin") String type,
//						  @RequestParam(value="w", required=false,defaultValue="98") int width,
//						  @RequestParam(value="h", required=false,defaultValue="33") int height,
//						  @RequestParam(value="cl", required=false,defaultValue="4") int codeLength,
//						  HttpServletRequest request,
//						  HttpServletResponse reponse){
//		CpachaUtil cpachaUtil = new CpachaUtil(codeLength,width,height);
//		String generatorVCode = cpachaUtil.generatorVCode();
//		request.getSession().setAttribute("loginCapcha", generatorVCode);
//		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
//		try {
//			ImageIO.write(generatorRotateVCodeImage, "gif", reponse.getOutputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
