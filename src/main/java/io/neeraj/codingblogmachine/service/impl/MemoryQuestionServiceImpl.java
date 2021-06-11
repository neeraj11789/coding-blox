package io.neeraj.codingblogmachine.service.impl;

import io.neeraj.codingblogmachine.entity.Level;
import io.neeraj.codingblogmachine.entity.Question;
import io.neeraj.codingblogmachine.service.QuestionService;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryQuestionServiceImpl implements QuestionService {

    private static List<Question> memoryQuestions = new ArrayList<>();
    private static int counter = 0;

    @Override
    public void createQuestion(final Level level, final Integer score) {
        Question question = getQuestion( level, score );
        memoryQuestions.add( question );
    }

    private Question getQuestion(Level level, Integer score) {
        final Question question = new Question();
        question.setQuestion( "Question" + counter );
        question.setAnswer( "Answer" + counter );
        question.setLevel( level );
        question.setScore( score );
        return question;
    }

    @Override
    public List<Question> listQuestion(Optional<Level> level) {
        List<Question> result = memoryQuestions;
        level.ifPresent( l -> {
            List<Question> collect = memoryQuestions.stream().filter( question -> question.getLevel().equals( l ) ).collect( Collectors.toList() );
            result.clear();
            result.addAll( collect );
        });
        return result;
    }
}
