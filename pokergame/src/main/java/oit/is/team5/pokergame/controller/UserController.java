package oit.is.team5.pokergame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

        @GetMapping("/register")
        public String registerForm() {
                return "register";
        }

        @PostMapping("/register")
        public String register(@RequestParam String username, Model model) {
                model.addAttribute("username", username);
                return "register";
        }

}