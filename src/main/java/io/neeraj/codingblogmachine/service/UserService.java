package io.neeraj.codingblogmachine.service;

import io.neeraj.codingblogmachine.entity.User;

public interface UserService {

    void createUser(final User user);

    User getUser(final String userId);
}
