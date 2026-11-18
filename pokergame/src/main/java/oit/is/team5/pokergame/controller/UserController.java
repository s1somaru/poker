package oit.is.team5.pokergame.controller;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.team5.pokergame.model.UserInfo;
import oit.is.team5.pokergame.model.UserInfoMapper;

@Controller
public class UserController {

  @Autowired
  UserInfoMapper userInfoMapper;

  @GetMapping("/register")
  public String registerForm() {
    return "register";
  }

  @PostMapping("/register")
  public String register(@RequestParam String username, Model model) {

    if (userInfoMapper.countByUsername(username) > 0) {
      model.addAttribute("error", "そのユーザー名はすでに使われています");
      return "register";
    }
    userInfoMapper.insertUser(username);
    model.addAttribute("username", username);
    return "register";
  }

  /*
   * @PostMapping("step3")
   *
   * @Transactional
   * public String sample43(@RequestParam String chamberName, ModelMap model,
   * Principal prin) {
   * String loginUser = prin.getName(); // ログインユーザ情報
   * Chamber chamber3 = new Chamber();
   * chamber3.setChamberName(chamberName);
   * chamber3.setUserName(loginUser);
   * chamberMapper.insertChamber(chamber3);
   * model.addAttribute("chamber3", chamber3);
   * // System.out.println("ID:" + chamber3.getId());
   * return "sample43.html";
   * }
   */

}
