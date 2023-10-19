package kr.co.ajoutee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {

    @Id
    @Column(name ="TODO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Date updated;//등록일

    @Column(nullable = false)
    private Boolean completed;


}
