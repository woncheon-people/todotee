package kr.co.ajoutee.todotee.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.ajoutee.todotee.domain.TodoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String title;
    private Boolean completed;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime completed_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime created_at;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updated_at;


    static public TodoResponseDto of(TodoEntity todo) {
        TodoResponseDto todoResponseDto = new TodoResponseDto();
        todoResponseDto.setId(todo.getId());
        todoResponseDto.setTitle(todo.getTitle());
        todoResponseDto.setCompleted(todo.getCompleted());
        todoResponseDto.setCompleted_at(todo.getComplete_at());
        todoResponseDto.setCreated_at(todo.getCreated_at());
        todoResponseDto.setUpdated_at(todo.getUpdated_at());

        return todoResponseDto;
    }
}

