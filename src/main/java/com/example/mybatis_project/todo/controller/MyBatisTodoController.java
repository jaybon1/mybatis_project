package com.example.mybatis_project.todo.controller;

import com.example.mybatis_project.todo.dto.TodoDTO;
import com.example.mybatis_project.todo.service.MyBatisTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MyBatisTodoController {

    private final MyBatisTodoService myBatisTodoService;

    @GetMapping("/todoList")
    public String select(Model model) {
        List<TodoDTO.ResBasic> dtoList = myBatisTodoService.findByDeleteYn("N");
        model.addAttribute("todoList", dtoList);
        model.addAttribute("todoSize", dtoList.stream().filter(todo -> todo.getDoneYn().equals("N")).count());
        return "todoList";
    }

}
