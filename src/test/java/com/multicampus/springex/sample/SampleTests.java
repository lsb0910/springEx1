package com.multicampus.springex.sample;

import com.multicampus.springex.sample.SampleDAOImpl;
import com.multicampus.springex.sample.SampleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class) //spring-test를 이용하기 위한 설정 Junit5버전
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml") //빈의 위치
public class SampleTests {

    @Autowired //스프링에서 사용하는 의존성 주입 어노테이션  "해당 타입의 빈이 존재한다면 여기에 주입해 주기를 원한다."
    private SampleService sampleService;

    @Autowired
    private SampleDAOImpl sampleDAO;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testService() {
        log.info(sampleService);
        Assertions.assertNotNull(sampleService);
    }

    @Test
    public void testSampleDAO() {
        log.info(sampleDAO);
        Assertions.assertNotNull(sampleDAO);
    }

    @Test
    public void testConnection() throws Exception { //testConnection
        Connection con = dataSource.getConnection();//java.sql.Connection
        log.info(con);
        Assertions.assertNotNull(con);
        con.close();
    }

}
