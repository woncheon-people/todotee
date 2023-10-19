package kr.co.ajoutee.dto;


import kr.co.ajoutee.domain.TodoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class TodoResponse<T> {

    private Long id;
    private String title;
    private Date updated;
    private Boolean completed;

    private String url;

    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.updated = todoEntity.getUpdated();
        this.completed = todoEntity.getCompleted();
        this.url = "http://localhost:8080/" + this.id;
    }
    }
