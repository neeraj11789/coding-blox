package io.neeraj.codingblogmachine.service.impl;

import io.neeraj.codingblogmachine.constant.WebException;
import io.neeraj.codingblogmachine.entity.*;
import io.neeraj.codingblogmachine.service.ContestService;
import io.neeraj.codingblogmachine.service.QuestionService;
import io.neeraj.codingblogmachine.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MemoryContestServiceImpl implements ContestService {

    private static final int RANDOM_QUESTIONS = 3;

    private static List<Contest> memoryContestList = new ArrayList<>();

    private static int contestId = 0;

    private static List<ContestParticipant> memoryContestParticipantList = new ArrayList<>();

    @NonNull
    private UserService userService;

    @NonNull
    private QuestionService questionService;

    @Override
    public void createContest(String contestName, Level level, User creator) {
        Contest contest = getContest( contestName, level, creator );
        // @todo: add validation - duplicate etc
        memoryContestList.add( contest );
        var contestParticipant = new ContestParticipant(contest, creator);
        memoryContestParticipantList.add( contestParticipant );
    }

    private Contest getContest(String contestName, Level level, User creator) {
        final Contest contest = new Contest( contestName, creator, level);
        contest.setId( contestId++ );
        return contest;
    }

    @Override
    public List<Contest> listContest(Optional<Level> level) {
        List<Contest> result = memoryContestList;
        level.ifPresent( l -> {
            final List<Contest> collect = memoryContestList.stream().filter( contest -> contest.getLevel().equals( l ) ).collect( Collectors.toList() );
            result.clear();
            result.addAll( collect );
        } );
        return memoryContestList;
    }

    @Override
    public void attendContest(long contestId, String userId) {
        User user = userService.getUser( userId );
        Optional<Contest> optionalContest = getContestById( contestId );
        if(optionalContest.isEmpty())
            throw new WebException( 400, "Contest Not Present" );
        Contest contest = optionalContest.get();
        ContestParticipant contestParticipant = new ContestParticipant( contest, user );
        memoryContestParticipantList.add( contestParticipant );
    }

    private Optional<Contest> getContestById(long contestId) {
        return memoryContestList.stream().filter( contest -> contest.getId() == contestId ).findFirst();
    }

    @Override
    public void runContest(long contestId, String creatorName) {
        // change the contest state
        // validate user who created
        // generate random question
        // assign questions to user
        // update score
        Optional<Contest> optionalContest = getContestById( contestId );
        if(optionalContest.isEmpty())
            throw new WebException( 400, "Contest Not Present" );
        Contest contest = optionalContest.get();
        User user = userService.getUser( creatorName );
        if(!contest.getCreator().equals( user ))
            throw new WebException( 400, "Only Creator of contest can run the contest"  );

        List<Question> questionList = questionService.listQuestion( Optional.of( contest.getLevel() ) );
        List<ContestParticipant> contestParticipants = memoryContestParticipantList.stream().filter( contestParticipant -> contestParticipant.getContest().equals( contest ) ).collect( Collectors.toList() );

        // edge case
        if(questionList.size() < RANDOM_QUESTIONS)
            throw new WebException(400, "Not Enough Questions for running contest" );

        contestParticipants.forEach( contestParticipant -> {
            Collections.shuffle( questionList );
            // updateScore
            List<Question> questions = questionList.subList( 0, 2 );
            updateScore(questions, contestParticipant, contest);
        } );
    }

    private void updateScore(List<Question> questions, ContestParticipant contestParticipant, Contest contest) {
        int sum = questions.stream().map( Question::getScore ).mapToInt( Integer::intValue ).sum();
        contestParticipant.setScore( sum );
        System.out.println(contestParticipant);
        updateUserScore(sum, contestParticipant.getParticipant(), contest);
    }

    private void updateUserScore(int sum, User user, Contest contest) {
        user.setScore( user.getScore() + sum +  contest.getLevel().ordinal());
        System.out.println(user);
    }
}
