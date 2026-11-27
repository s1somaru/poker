package oit.is.team5.pokergame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 固定4人対戦テーブルの準備状況（席と参加人数）を表示する。
 * 席への参加は #8 で TableService として追加する予定。
 */
@Controller
public class TableController {

  static final int SEAT_COUNT = 4;

  @GetMapping("/tables")
  public String lobby(Model model) {
    // 参加処理は #8 で作るため、現時点の参加者は0人
    List<String> participants = new ArrayList<>();

    // 席ごとの参加者名。空席は null
    List<String> seats = new ArrayList<>();
    for (int i = 0; i < SEAT_COUNT; i++) {
      seats.add(i < participants.size() ? participants.get(i) : null);
    }

    model.addAttribute("seats", seats);
    model.addAttribute("participantCount", participants.size());
    model.addAttribute("seatCount", SEAT_COUNT);
    model.addAttribute("waiting", participants.size() < SEAT_COUNT);
    return "table-lobby";
  }

}
