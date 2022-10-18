package com.example.springboot;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class HelloController {

//	public static StringBuffer memBuf = new StringBuffer();

	@GetMapping("/")
	public String index( Model model) {
		return "index";
	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		System.out.println("param name=" + name);
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/getdata")
	public String getdata(@RequestHeader("User-Agent") String userAgent, Model model) {
		String result = null ;
//		if (userAgent.indexOf("Chrome") != -1 ){
			String url = "http://node-express.default.dev.abc.tanzukorea.xyz/";
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject(url, String.class);
//		}
		model.addAttribute("result", result.length());
		return "getdata";
	}

	@GetMapping("/heavy")
	public String heavy(@RequestParam(name="time", required=false, defaultValue="1") long time, Model model) {
		//System.out.println("Sleeping duration =" + time);
		try {
			Thread.sleep(time * 1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Wake up duration =" + time);
		model.addAttribute("time", time);
		return "heavy";
	}

}