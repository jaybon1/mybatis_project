package com.example.mybatis_project.todo.repository;

import com.example.mybatis_project.todo.entity.TodoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MyBatisTodoRepository {
        String FULL_COLUMNS = " idx, content, done_yn, delete_yn, create_date, update_date, delete_date ";

        @Select(" SELECT " + FULL_COLUMNS +
                        " FROM todo " +
                        " WHERE delete_yn = #{deleteYn} ")
        List<TodoEntity> findByDeleteYn(String deleteYn);

        @Select(" SELECT " + FULL_COLUMNS +
                        " FROM todo " +
                        " WHERE delete_yn = #{deleteYn} " +
                        " AND done_yn = #{doneYn} ")
        List<TodoEntity> findByfindByDeleteYnAndDoneYn(String deleteYn, String doneYn);

        @Select(" SELECT " + FULL_COLUMNS +
                        " FROM todo " +
                        " WHERE idx = #{idx} ")
        TodoEntity findByIdx(Integer idx);

        @Insert(" INSERT INTO todo " +
                        " ( content, done_yn, delete_yn, create_date ) " +
                        " VALUES " +
                        " ( #{content}, #{doneYn}, #{deleteYn}, #{createDate}) ")
        Integer insert(TodoEntity todoEntity);

        @Update(" UPDATE todo " +
                        " SET " +
                        " content = #{content}, " +
                        " done_yn = #{doneYn}, " +
                        " delete_yn = #{deleteYn}, " +
                        " create_date = #{createDate}, " +
                        " update_date = #{updateDate}, " +
                        " delete_date = #{deleteDate} " +
                        " WHERE	idx = #{idx}")
        Integer update(TodoEntity todoEntity);

}
