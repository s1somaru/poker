package oit.is.team5.pokergame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import oit.is.team5.pokergame.model.UserInfoMapper;

/**
 * 登録済みユーザー名による簡易ログインを扱う。
 * ログインに成功したユーザー名はセッションの "username" に保持する。
 */
@Controller
public class LoginController {

  static final String SESSION_USERNAME = "username";

  @Autowired
  UserInfoMapper userInfoMapper;

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, HttpSession session, Model model) {
    if (userInfoMapper.countByUsername(username) == 0) {
      model.addAttribute("username", username);
      model.addAttribute("error", "そのユーザー名は登録されていません");
      return "login";
    }
    session.setAttribute(SESSION_USERNAME, username);
    return "redirect:/tables";
  }

}
