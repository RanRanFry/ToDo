package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDto;
import com.example.todo.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class JdbcTemplateTodoRepository implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoResponseDto saveTodo(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todos").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", todo.getUserName());
        parameters.put("password", todo.getPassword());
        parameters.put("title", todo.getTitle());
        parameters.put("contents", todo.getContents());
        parameters.put("createdAt", todo.getCreatedAt());
        parameters.put("modifiedAt", todo.getModifiedAt());

        //저장 후 생성된 key값 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));

        TodoResponseDto responseDto = new TodoResponseDto(key.longValue(), todo.getUserName(), todo.getTitle(), todo.getContents(), todo.getModifiedAt());
        return responseDto;

    }

    @Override
    public List<TodoResponseDto> findAllTodos() {

        return  jdbcTemplate.query("select * from todos order by modifiedAt DESC", todoRowMapper());
    }

    @Override
    public Optional<Todo> findTodoById(Long id) {
        List <Todo> result =  jdbcTemplate.query("select * from todos where id = ?", todowRowMapperV2(), id);

        return result.stream().findAny();
    }

    //단건 수정
    @Override
    public int updateText(Long id, String userName, String password, String contents) {

//        String targetPassword= jdbcTemplate.query("select password from todos where id = ?", todowRowMapperV2(), id);
        String sql = "SELECT password FROM todos WHERE id=?";

        String targetPassword = (String) jdbcTemplate.queryForObject(  sql, String.class, id);

        if ( !targetPassword.equals(password)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }
        LocalDateTime nowModified = LocalDateTime.now();


        return jdbcTemplate.update("update todos set userName=? ,contents = ?, modifiedAt =? where id = ? ", userName, contents,nowModified, id);
    }


    private RowMapper<TodoResponseDto> todoRowMapper(){
        return new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoResponseDto(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("modifiedAt").toLocalDateTime()



                );
            }
        };

    }

    private RowMapper <Todo> todowRowMapperV2(){
        return new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Todo(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("modifiedAt").toLocalDateTime()
                );
            }
        };
    }



}
