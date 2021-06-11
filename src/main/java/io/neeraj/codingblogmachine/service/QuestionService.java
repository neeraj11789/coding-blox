package io.neeraj.codingblogmachine.service;

import io.neeraj.codingblogmachine.entity.Level;
import io.neeraj.codingblogmachine.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    void createQuestion(Level level, Integer score);

    List<Question> listQuestion(Optional<Level> level);
}
