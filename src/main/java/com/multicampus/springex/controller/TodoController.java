package com.multicampus.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.multicampus.springex.dto.PageRequestDTO;
import com.multicampus.springex.dto.TodoDTO;
import com.multicampus.springex.service.TodoService;

import javax.validation.Valid;


@Controller //스프링 MVC에서 컨트롤러 역할, 스프링의 빈(Bean)으로 등록
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor //final로 지정해서 지정된 것만 주입하겠다.

public class TodoController {
    // injection을 안해서 데이터 베이스에 값이 넣어지지 않은 것
    private final TodoService todoService;

   /* @RequestMapping("/list") //localhost:8090/todo/list
    public void list(Model model){
        log.info("todo_list");
        // TodoService에서 리턴한 List<TodoDTO> getAll();을 model에다가 담기
        model.addAttribute("dtoList", todoService.getAll());
        //model 'dtoList' 이름으로 목록 데이터가 담겨있다. => list.jsp가 처리해줘야 함
    }
*/
    //@RequestMapping(value="/register", method = RequestMethod.GET) //localhost:8090/todo/register
    @GetMapping("/register")
    /*제대로 불러오는지 확인하는 작업, 제대로 불러오면 콘솔에 뜬다. */
    public void registerGET() {
        log.info("GET todo register.......");
    }


    // register은 post와 get방식 따로 따로, 객체를 알아서 넣어줌
    @PostMapping("/register")
    //서버 사이드 검증 redirect에 바인딩
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");        // addFlashAttribute : 한 번만 일회성으로 사용자에게 에러를 보여줌 a
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/todo/register";
        }
        // 추가해주기 => 데이터베이스에 드디어 추가 가능
        log.info(todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }



    // HTTP GET 요청을 처리하며, "/read" "/modify" 경로로 요청이 들어올 때 호출
    @GetMapping({"/read", "/modify"})
    // model은 뷰와 컨트롤러 간의 데이터 교환을 위한 객체
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model){
        // todoService라는 서비스 객체를 통해 tno에 해당하는 할 일 데이터를 조회하는 역할
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        // 조회한 할 일 데이터를 model에 "dto"라는 이름으로 추가합니다. 이렇게 모델에 데이터를 추가하면 해당 데이터는 뷰로 전달되어 뷰에서 사용가능
        model.addAttribute("dto", todoDTO );

    }



    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("-------------remove------------------");
        log.info("tno: " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list?";
    }


    @PostMapping("/modify")
    public String modify(
            PageRequestDTO pageRequestDTO,
            @Valid TodoDTO todoDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("tno", todoDTO.getTno() );
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/todo/read";
    }




    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }


}
