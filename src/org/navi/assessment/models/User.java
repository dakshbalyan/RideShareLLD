package org.navi.assessment.models;

import org.navi.assessment.enums.Gender;

public class User {
    private final String userName;
    private final String name;
    private final Gender gender;
    private final int userAge;
    private int numOfRidesOffered;
    private int numOfRidesTaken;

    public User(String userName, String name, Gender gender, int userAge) {
        this.userName = userName;
        this.name = name;
        this.gender = gender;
        this.userAge = userAge;
        this.numOfRidesOffered = 0;
        this.numOfRidesTaken = 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getUserAge() {
        return userAge;
    }

    public void incrementRidesTaken(){
        this.numOfRidesTaken++;
    }

    public void incrementRidesOffered(){
        this.numOfRidesOffered++;
    }

//    public void decrementRidesTaken(){
//        this.numOfRidesTaken--;
//    }
//
//    public void decrementRidesOffered(){
//        this.numOfRidesOffered--;
//    }

    public int getNumOfRidesOffered() {
        return numOfRidesOffered;
    }

    public int getNumOfRidesTaken() {
        return numOfRidesTaken;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", userAge=" + userAge +
                '}';
    }

    public void printRideStats(){
        System.out.printf("%s : %d Taken, %d Offered %n",
                this.name,
                this.numOfRidesTaken,
                this.numOfRidesOffered);

    }

}
