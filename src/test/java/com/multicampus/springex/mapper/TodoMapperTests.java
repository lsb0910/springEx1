package com.multicampus.springex.mapper;

import com.multicampus.springex.domain.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("스프링 TodoTest")
                .dueDate(LocalDate.of(2023,8,9))
                .writer("user1")
                .build();
        todoMapper.insert(todoVO);

    }

    @Test
    public void testSelectAll() {
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));

    }

    // 귀찮아도 데이터 받아오는것은 체크해야한다!!!
    @Test
    public void testSelectOne(){
        TodoVO todovo = todoMapper.selectOne(3L);  //Long이니까 3L로 표기
        log.info(todovo);

    }
}






