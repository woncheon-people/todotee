package kr.co.ajoutee.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    @NotEmpty
    private String title;
    private Boolean completed;
    private Date updated;

}
