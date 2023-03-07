package com.example.mybatis_project.todo.controller;

import com.example.mybatis_project.todo.dto.TodoDTO;
import com.example.mybatis_project.todo.service.MyBatisTodoApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyBatisTodoApiController {

    private final MyBatisTodoApiService myBatisTodoApiService;

    @GetMapping("/todoList")
    public HttpEntity<?> select() {
        return myBatisTodoApiService.select("N");
    }

    @PostMapping("/todoList")
    public HttpEntity<?> insert(@RequestBody TodoDTO.ReqBasic reqDto) {
        log.info(reqDto.getContent() + "를 등록합니다.");
        return myBatisTodoApiService.insert(reqDto);
    }

    @PutMapping("/todoList")
    public HttpEntity<?> update(@RequestBody TodoDTO.ReqUpdate reqDto) {
        log.info(reqDto.getIdx() + "번 할 일을 수정합니다.");
        return myBatisTodoApiService.updateDoneYn(reqDto);
    }

    @DeleteMapping("/todoList")
    public HttpEntity<?> delete(@RequestBody TodoDTO.ReqDelete reqDto) {
        log.error(reqDto.getIdx() + "번 할 일을 삭제합니다.");
        return myBatisTodoApiService.updateDeleteYn(reqDto);
    }

}
