package admin.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartupController {

	@RequestMapping("/")
	public String startApp(){
		return "welcome";
	}
}
