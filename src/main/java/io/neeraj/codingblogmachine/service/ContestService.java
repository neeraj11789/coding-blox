package io.neeraj.codingblogmachine.service;

import io.neeraj.codingblogmachine.entity.Contest;
import io.neeraj.codingblogmachine.entity.Level;
import io.neeraj.codingblogmachine.entity.User;

import java.util.List;
import java.util.Optional;

public interface ContestService {
    void createContest(String contestName, Level level, User creator);

    List<Contest> listContest(Optional<Level> level);

    void attendContest(long contestId, String userName);

    void runContest(long contestId, String creatorName);

    // @todo: WithdrawContest
    // @todo: ContestHistory
}
