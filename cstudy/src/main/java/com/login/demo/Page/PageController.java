package com.login.demo.Page;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String home() {
		return "/home";
	}
	@GetMapping("/home")
	public String realHome() {
		return "/home";
	}
	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}
	@GetMapping("/user")
	public String user() {
		return "/user";
	}
	@GetMapping("/login")
	public String about() {
		return "/login";
	}
	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}
	@ResponseBody
	@RequestMapping(value ="/about", method = RequestMethod.GET)
	public void responseRestAPI(HttpServletResponse response) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("title", "정확한 서비스 전 로그인 페이지 연습 ");
		map.put("contents", "사실 로그인 부분도 요즘은 API로 개발하는 추세입니다 ");
		
		String jsonStr = mapper.writeValueAsString(map);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(jsonStr);
		
	}

}
