package oit.is.team5.pokergame.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import oit.is.team5.pokergame.model.UserInfoMapper;

// アプリ本体はSpring Securityを使っていないため、HomeControllerTestと同じくフィルタを外す。
@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
class LoginControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UserInfoMapper userInfoMapper;

  // AC1
  @Test
  void ログイン画面を開くとユーザー名入力欄とログインボタンが表示される() throws Exception {
    mockMvc.perform(get("/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andExpect(content().string(containsString("name=\"username\"")))
        .andExpect(content().string(containsString("type=\"submit\"")));
  }

  // AC2
  @Test
  void 登録済みユーザー名でログインすると対戦席一覧へ遷移する() throws Exception {
    when(userInfoMapper.countByUsername("akari")).thenReturn(1);

    mockMvc.perform(post("/login").param("username", "akari"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/tables"))
        .andExpect(request().sessionAttribute("username", "akari"));
  }

  // AC3
  @Test
  void 未登録のユーザー名でログインするとエラーメッセージが表示される() throws Exception {
    when(userInfoMapper.countByUsername("nobody")).thenReturn(0);

    mockMvc.perform(post("/login").param("username", "nobody"))
        .andExpect(status().isOk())
        .andExpect(view().name("login"))
        .andExpect(content().string(containsString("そのユーザー名は登録されていません")))
        .andExpect(request().sessionAttributeDoesNotExist("username"));
  }

}
