package oit.is.team5.pokergame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * アプリの入口となる初期画面を返す。
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
