package io.neeraj.codingblogmachine.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class User extends BaseDomain{

    @NonNull
    @NotBlank
    private String userId;

    private long score = 0L;
}
