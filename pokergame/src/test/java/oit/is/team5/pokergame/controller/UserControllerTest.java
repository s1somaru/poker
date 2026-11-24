package oit.is.team5.pokergame.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import oit.is.team5.pokergame.model.UserInfoMapper;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UserInfoMapper userInfoMapper;

  @Test
  void 登録画面を開くとユーザー名入力フォームが表示される() throws Exception {
    mockMvc.perform(get("/register"))
        .andExpect(status().isOk())
        .andExpect(view().name("register"))
        .andExpect(content().string(containsString("name=\"username\"")))
        .andExpect(content().string(containsString("type=\"submit\"")));
  }

  @Test
  void ユーザー名を送信すると入力値を保持して登録画面が返される() throws Exception {
    mockMvc.perform(post("/register").param("username", "alice"))
        .andExpect(status().isOk())
        .andExpect(view().name("register"))
        .andExpect(content().string(containsString("value=\"alice\"")));

    verify(userInfoMapper).insertUser("alice");
  }

  @Test
  void 登録済みのユーザー名を送信するとエラーを表示して保存しない() throws Exception {
    when(userInfoMapper.countByUsername("akari")).thenReturn(1);

    mockMvc.perform(post("/register").param("username", "akari"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("そのユーザー名はすでに使われています")));

    verify(userInfoMapper, never()).insertUser("akari");
  }

}
