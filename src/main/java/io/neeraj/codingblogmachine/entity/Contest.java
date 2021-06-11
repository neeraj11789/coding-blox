package io.neeraj.codingblogmachine.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Contest extends BaseDomain{

    @NonNull
    @NotBlank
    private String ContestName;

    @NonNull
    private User creator;

    @NonNull
    private Level level;

    private ContestState contestState = ContestState.created;

    private List<Question> questions = new ArrayList<>();
}
