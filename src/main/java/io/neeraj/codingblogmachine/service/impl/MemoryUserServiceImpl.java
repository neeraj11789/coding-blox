package io.neeraj.codingblogmachine.service.impl;

import io.neeraj.codingblogmachine.constant.WebException;
import io.neeraj.codingblogmachine.entity.User;
import io.neeraj.codingblogmachine.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class MemoryUserServiceImpl implements UserService{

    private static Map<String, User> userMap = new HashMap<>();

    @Override
    public void createUser(User user) {
        if(userMap.containsKey( user.getUserId() ))
            throw new WebException(400, "User Already Present");
        userMap.put( user.getUserId(), user );
    }

    @Override
    public User getUser(String userId) {
        if(!userMap.containsKey( userId ))
            throw new WebException(404, "User Does Not Exist");
        return userMap.get( userId );
    }
}
