package com.example.mybatis_project.todo.service;

import com.example.mybatis_project.todo.dto.TodoDTO;
import com.example.mybatis_project.todo.repository.MyBatisTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyBatisTodoService {

    private final MyBatisTodoRepository myBatisTodoRepository;

    public List<TodoDTO.ResBasic> findByDeleteYn(String deleteYn) {
        return myBatisTodoRepository.findByDeleteYn(deleteYn)
                .stream()
                .map(TodoDTO.ResBasic::fromEntity)
                .collect(Collectors.toList());
    }

}
