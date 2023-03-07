package com.example.mybatis_project.todo.controller;

import com.example.mybatis_project.todo.dto.TodoDTO;
import com.example.mybatis_project.todo.service.MyBatisTodoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyBatisTodoApiController {

    private final MyBatisTodoApiService myBatisTodoApiService;

    @PostMapping("/todoList")
    public HttpEntity<?> insert(@RequestBody TodoDTO.ReqBasic reqDto) {
        System.out.println(reqDto.getContent());
        return myBatisTodoApiService.insert(reqDto);
    }

    @PutMapping("/todoList")
    public HttpEntity<?> update(@RequestBody TodoDTO.ReqUpdate reqDto) {
        return myBatisTodoApiService.updateDoneYn(reqDto);
    }

    @DeleteMapping("/todoList")
    public HttpEntity<?> delete(@RequestBody TodoDTO.ReqDelete reqDto) {
        return myBatisTodoApiService.updateDeleteYn(reqDto);
    }

}
