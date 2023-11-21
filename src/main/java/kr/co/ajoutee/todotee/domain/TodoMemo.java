package kr.co.ajoutee.todotee.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class TodoMemo extends BasicEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false)
    @NonNull
    private String memo;

    @Column(updatable = false)
    private Boolean completed;
//    true false

    @Column(updatable = false)
    private LocalDateTime complete_at;

    @Builder
    public TodoMemo(String title,String memo,Boolean completed) {
        this.title = title;
        this.memo = memo;
        this.completed = completed;
        setComplete_at();
    }

    public void setComplete_at() {
        if (this.getCompleted() != null && this.getCompleted()) {
            this.complete_at = LocalDateTime.now();
        } else {
            this.complete_at = null;
        }
    }

    public void update(String title,String memo,Boolean completed) {
        this.title = title;
        this.memo = memo;
        this.completed = completed;
        setComplete_at();
    }
}
