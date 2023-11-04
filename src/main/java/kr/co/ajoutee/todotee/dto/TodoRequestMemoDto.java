package kr.co.ajoutee.todotee.dto;

import jakarta.validation.constraints.NotEmpty;
import kr.co.ajoutee.todotee.domain.TodoMemo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoRequestMemoDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String memo;

    private Boolean completed;

    public TodoMemo toEntity(){
        return TodoMemo.builder()
                .title(title)
                .memo(memo)
                .completed(true)
                .build();
    }

    public TodoRequestMemoDto(String title, String memo, Boolean completed) {
        this.title = title;
        this.memo = memo;
        this.completed = completed;
    }
}
