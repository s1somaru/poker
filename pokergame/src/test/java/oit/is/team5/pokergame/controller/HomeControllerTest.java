package oit.is.team5.pokergame.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// アプリ本体はSpring Securityを使っていない（PR #20で無効化）。
// テスト依存にsecurity-testが残っておりテスト時だけ認証がかかるため、フィルタを外して本体と同じ条件にする。
// ログイン機能（#6）でSecurityを戻すときは、この設定を見直すこと。
@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc(addFilters = false)
class HomeControllerTest {

	@Autowired
	MockMvc mockMvc;

	// AC1: トップページのURLを開くと、最初のページが表示される
	@Test
	void トップページを開くと最初のページが表示される() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Poker Game")));
	}

	// AC2: ルートURLへ遷移すると、対応する初期画面が返される
	@Test
	void ルートURLへ遷移すると初期画面が返される() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

}
