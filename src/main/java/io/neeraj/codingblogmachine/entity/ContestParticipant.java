package io.neeraj.codingblogmachine.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ContestParticipant {

    @NonNull
    private Contest contest;

    @NonNull
    private User participant;

    private Integer Score = 0;

}
