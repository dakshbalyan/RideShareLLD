package org.navi.assessment.repository;

import org.navi.assessment.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> userNameToUserMap;

    public UserRepository() {
        this.userNameToUserMap = new HashMap<>();
    }

    public void save(User user) {
        userNameToUserMap.put(user.getUserName(), user);
    }

    public User findUserByUserName(String userName){
        return userNameToUserMap.getOrDefault(userName, null);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userNameToUserMap.values());
    }
}
