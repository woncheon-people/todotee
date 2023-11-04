package kr.co.ajoutee.todotee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.co.ajoutee.todotee.domain.TodoMemo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseMemoDto {

        private Long id;
        private String title;
        private String memo;


        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime completed_at;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime created_at;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updated_at;


        static public TodoResponseMemoDto of(TodoMemo todo) {
            TodoResponseMemoDto todoResponseMemoDto = new TodoResponseMemoDto();
            todoResponseMemoDto.setId(todo.getId());
            todoResponseMemoDto.setTitle(todo.getTitle());
            todoResponseMemoDto.setMemo(todo.getMemo());
            return todoResponseMemoDto;
        }
}
