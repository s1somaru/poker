package oit.is.team5.pokergame.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class DatabaseConfigTest {

  @Autowired
  DataSource dataSource;

  @Test
  void 起動時にDB接続先が正しく設定される() throws Exception {
    try (Connection connection = dataSource.getConnection()) {
      assertEquals("jdbc:h2:mem:pokergame", connection.getMetaData().getURL());
    }
  }

  @Test
  void 有効な設定でDBに正常に接続できる() throws Exception {
    try (Connection connection = dataSource.getConnection()) {
      assertTrue(connection.isValid(1));
    }
    Integer result = new JdbcTemplate(dataSource).queryForObject("SELECT 1", Integer.class);
    assertEquals(1, result);
  }
}
