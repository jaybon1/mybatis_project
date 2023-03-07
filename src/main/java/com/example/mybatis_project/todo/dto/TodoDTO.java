package com.example.mybatis_project.todo.dto;

import com.example.mybatis_project.todo.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TodoDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqBasic {
        private String content;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqUpdate {
        private Integer idx;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReqDelete {
        private Integer idx;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResBasic {
        private Integer idx;
        private String content;
        private String doneYn;
        private String deleteYn;

        public static TodoDTO.ResBasic fromEntity(TodoEntity todoEntity) {
            return TodoDTO.ResBasic.builder()
                    .idx(todoEntity.getIdx())
                    .content(todoEntity.getContent())
                    .doneYn(todoEntity.getDoneYn())
                    .deleteYn(todoEntity.getDeleteYn())
                    .build();
        }

        public TodoEntity toEntity() {
            return TodoEntity.builder()
                    .idx(this.idx)
                    .content(this.content)
                    .doneYn(this.doneYn)
                    .deleteYn(this.deleteYn)
                    .build();
        }
    }
}
