package io.neeraj.codingblogmachine.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Level {
    LOW(-50),
    MEDIUM(-30),
    HIGH(0);

    int scoreLogic;
}
