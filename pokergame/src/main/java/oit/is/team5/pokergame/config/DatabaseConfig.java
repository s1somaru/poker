package oit.is.team5.pokergame.config;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DB接続の基本設定。
 * 接続先は application.properties の spring.datasource.* で指定する。
 * 起動時に一度接続し、接続できない場合は起動を失敗させる。
 */
@Configuration
public class DatabaseConfig {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

  @Bean
  ApplicationRunner databaseConnectionCheck(DataSource dataSource) {
    return args -> {
      try (Connection connection = dataSource.getConnection()) {
        logger.info("DB connected: {}", connection.getMetaData().getURL());
      }
    };
  }
}
