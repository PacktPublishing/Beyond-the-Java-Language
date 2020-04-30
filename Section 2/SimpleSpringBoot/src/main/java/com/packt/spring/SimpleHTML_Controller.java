package com.packt.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleHTML_Controller
{

	@RequestMapping("/")
	public String index(Model model)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy : HH:mm:ss:SSS z");
		Date now = new Date();
		String dateStr = dateFormat.format(now);
		model.addAttribute("time", dateStr);
		return "index:  Date: " + dateStr;
	}

}
