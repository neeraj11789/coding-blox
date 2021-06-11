package io.neeraj.codingblogmachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseDomain{
    // @todo: implement different types of question

    private String question = UUID.randomUUID().toString();

    @NonNull
    private Integer score ;

    private String answer = UUID.randomUUID().toString();;

    @NonNull
    private Level level;

}
