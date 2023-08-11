package com.multicampus.springex.service;

import com.multicampus.springex.dto.TodoDTO;
import com.multicampus.springex.dto.PageRequestDTO;
import com.multicampus.springex.dto.PageResponseDTO;


public interface TodoService {

    void register(TodoDTO todoDTO);

    //List<TodoDTO> getAll();

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    // 리턴타입 getOne(파라미터) 메소드 추가
    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}

