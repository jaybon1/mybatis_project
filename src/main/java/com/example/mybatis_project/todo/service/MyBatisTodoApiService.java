package com.example.mybatis_project.todo.service;

import com.example.mybatis_project.common.ResDTO;
import com.example.mybatis_project.todo.dto.TodoDTO;
import com.example.mybatis_project.todo.entity.TodoEntity;
import com.example.mybatis_project.todo.repository.MyBatisTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyBatisTodoApiService {

    private final MyBatisTodoRepository myBatisTodoRepository;


    public HttpEntity<?> select(String deleteYn) {
        return ResponseEntity
                .ok()
                .body(ResDTO.builder()
                        .code(0)
                        .message("조회에 성공하였습니다.")
                        .data(myBatisTodoRepository.findByDeleteYn(deleteYn)
                                .stream()
                                .map(TodoDTO.ResBasic::fromEntity)
                                .collect(Collectors.toList()))
                        .build());
    }

    @Transactional
    public HttpEntity<?> insert(TodoDTO.ReqBasic reqDto) {

        TodoEntity todoEntity = TodoEntity.builder()
                .content(reqDto.getContent())
                .doneYn("N")
                .deleteYn("N")
                .createDate(LocalDateTime.now())
                .build();

        try {
            myBatisTodoRepository.insert(todoEntity);
            return ResponseEntity
                    .ok()
                    .body(ResDTO.builder()
                            .code(0)
                            .message("등록에 성공하였습니다.")
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResDTO.builder()
                            .code(1)
                            .message("등록에 실패하였습니다.")
                            .build());
        }
    }

    @Transactional
    public HttpEntity<?> updateDoneYn(TodoDTO.ReqUpdate reqDto) {

        TodoEntity todoEntity = myBatisTodoRepository.findByIdx(reqDto.getIdx());

        if (todoEntity == null) {
            return ResponseEntity
                    .badRequest()
                    .body(ResDTO.builder()
                            .code(1)
                            .message("잘못된 요청입니다.")
                            .build());
        }

        todoEntity.setDoneYn("Y".equals(todoEntity.getDoneYn()) ? "N" : "Y");
        todoEntity.setUpdateDate(LocalDateTime.now());

        try {
            myBatisTodoRepository.update(todoEntity);
            return ResponseEntity
                    .ok()
                    .body(ResDTO.builder()
                            .code(0)
                            .message("수정에 성공하였습니다.")
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResDTO.builder()
                            .code(2)
                            .message("수정에 실패하였습니다.")
                            .build());
        }
    }

    @Transactional
    public HttpEntity<?> updateDeleteYn(TodoDTO.ReqDelete reqDto) {
        TodoEntity todoEntity = myBatisTodoRepository.findByIdx(reqDto.getIdx());

        if (todoEntity == null) {
            return ResponseEntity
                    .badRequest()
                    .body(ResDTO.builder()
                            .code(1)
                            .message("잘못된 요청입니다.")
                            .build());
        }

        todoEntity.setDeleteYn("Y");
        todoEntity.setDeleteDate(LocalDateTime.now());

        try {
            myBatisTodoRepository.update(todoEntity);
            return ResponseEntity
                    .ok()
                    .body(ResDTO.builder()
                            .code(0)
                            .message("삭제에 성공하였습니다.")
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .internalServerError()
                    .body(ResDTO.builder()
                            .code(2)
                            .message("삭제에 실패하였습니다.")
                            .build());
        }
    }

}
