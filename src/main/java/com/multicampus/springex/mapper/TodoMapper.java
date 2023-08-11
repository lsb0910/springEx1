
package com.multicampus.springex.mapper;

import com.multicampus.springex.domain.TodoVO;
import com.multicampus.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    //TodoVo를 파라미터로 입력받는 insert() 정의
    void insert(TodoVO todoVO);

    // 가장 최근에 등록된 글 순서대로 tbl_todo테이블의 모든 row를 가져온다. todolist_selectall 작업
    List<TodoVO> selectAll();

    // 조회 기능!!!
    TodoVO selectOne(Long tno);

    // 삭제 기능!!!
    void delete(Long tno);

    // 업데이트 기능!!!
    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}

