package oit.is.team5.pokergame.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// アプリ本体はSpring Securityを使っていないため、HomeControllerTestと同じくフィルタを外す。
@WebMvcTest(TableController.class)
@AutoConfigureMockMvc(addFilters = false)
class TableControllerTest {

  @Autowired
  MockMvc mockMvc;

  // AC1
  @Test
  void 対戦テーブルを開くと4人分の席情報と参加人数が表示される() throws Exception {
    mockMvc.perform(get("/tables"))
        .andExpect(status().isOk())
        .andExpect(view().name("table-lobby"))
        .andExpect(content().string(containsString("席1：空席")))
        .andExpect(content().string(containsString("席2：空席")))
        .andExpect(content().string(containsString("席3：空席")))
        .andExpect(content().string(containsString("席4：空席")))
        .andExpect(content().string(containsString("参加人数：0 / 4")));
  }

  // AC2
  @Test
  void 参加人数が3人以下なら待機中と表示される() throws Exception {
    mockMvc.perform(get("/tables"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("waiting", true))
        .andExpect(content().string(containsString("4人揃うまで待機中")));
  }

}
