package com.mahadahack.geolocalizacion;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
    class IndexController {
 
    @RequestMapping("/")
	String index() {
	return "index";
    }
}
