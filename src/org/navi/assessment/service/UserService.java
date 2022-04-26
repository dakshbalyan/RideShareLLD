package org.navi.assessment.service;

import org.navi.assessment.enums.Gender;
import org.navi.assessment.exceptions.ResourceExistenceException;
import org.navi.assessment.models.User;
import org.navi.assessment.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String userName, String name, int age, String gender) {
        try{
            if (checkUserExists(userName)){
                throw new ResourceExistenceException("User : " + userName + " already exist!");
            }

            Gender userGender = Gender.valueOf(gender);
            User user = new User(userName, name, userGender, age);
            userRepository.save(user);

        } catch ( ResourceExistenceException e ) {
            System.err.println(e.getMessage());
        }
        catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }

    public boolean checkUserExists(String userName){
        return userRepository.findUserByUserName(userName) != null;
    }

    public User getUserByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }

    public void printUserRideStats() {
        List<User> userList = userRepository.getAllUsers();
        userList.forEach(User :: printRideStats);
    }

}
