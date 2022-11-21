package com.team.passengerrailwaytransportation.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class TestDBConfig {

  private static EmbeddedPostgres embeddedPostgres;

  @Bean
  TestRestTemplate testRestTemplate(){
    return new TestRestTemplate();
  }

  @PostConstruct
  @Bean(name = "embeddedPostgresDataSource")
  public DataSource embeddedPostgres() throws IOException {
    if(Objects.isNull(embeddedPostgres )){
      embeddedPostgres = EmbeddedPostgres.builder().start();
    }
    return embeddedPostgres.getPostgresDatabase();
  }
}
