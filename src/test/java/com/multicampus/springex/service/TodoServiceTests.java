package com.multicampus.springex.service;


import com.multicampus.springex.domain.TodoVO;
import com.multicampus.springex.dto.PageRequestDTO;
import com.multicampus.springex.dto.TodoDTO;
import com.multicampus.springex.mapper.TodoMapper;
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
public class TodoServiceTests {
    @Autowired
    private TodoService todoService;
    private TodoMapper todoMapper;

    @Test
    public void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test Todo 1")
                .dueDate(LocalDate.now())
                .writer("user2")
                .build();
        todoService.register(todoDTO);

    }



 @Test
    public void testPaging(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(3).size(10).build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo-> log.info(vo));
    }



}
