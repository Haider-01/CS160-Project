package com.example.CS160Broque;

// This class creates an Account Object using data retrieved from the Database
// Newly created accounts will be initialized with 0 values.
public class Account {
    private String fullName;
    private String userName;
    private String password;
    private String phoneNumber;

    private double currentTotalBudget;
    private double currentBillsBudget;
    private double currentFoodBudget;
    private double currentEntertainmentBudget;
    private double currentOtherBudget;

    private double staticTotalBudget;
    private double staticBillsBudget;
    private double staticFoodBudget;
    private double staticEntertainmentBudget;
    private double staticOtherBudget;

    public Account(){
        this.fullName = "";
        this.userName = "";
        this.password = "";
        this.phoneNumber = "";

        this.currentTotalBudget = 0;
        this.currentBillsBudget = 0;
        this.currentFoodBudget = 0;
        this.currentEntertainmentBudget = 0;
        this.currentOtherBudget = 0;

        this.staticTotalBudget = 0;
        this.staticBillsBudget = 0;
        this.staticFoodBudget = 0;
        this.staticEntertainmentBudget = 0;
        this.staticOtherBudget = 0;
    }// Account Default Constructor

    public Account(String fullName, String userName, String password, String phoneNumber){
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }// Account Constructor

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCurrentTotalBudget() {
        return currentTotalBudget;
    }

    public void setCurrentTotalBudget(double currentTotalBudget) {
        this.currentTotalBudget = currentTotalBudget;
    }

    public double getCurrentBillsBudget() {
        return currentBillsBudget;
    }

    public void setCurrentBillsBudget(double currentBillsBudget) {
        this.currentBillsBudget = currentBillsBudget;
    }

    public double getCurrentFoodBudget() {
        return currentFoodBudget;
    }

    public void setCurrentFoodBudget(double currentFoodBudget) {
        this.currentFoodBudget = currentFoodBudget;
    }

    public double getCurrentEntertainmentBudget() {
        return currentEntertainmentBudget;
    }

    public void setCurrentEntertainmentBudget(double currentEntertainmentBudget) {
        this.currentEntertainmentBudget = currentEntertainmentBudget;
    }

    public double getCurrentOtherBudget() {
        return currentOtherBudget;
    }

    public void setCurrentOtherBudget(double currentOtherBudget) {
        this.currentOtherBudget = currentOtherBudget;
    }

    public double getStaticTotalBudget() {
        return staticTotalBudget;
    }

    public void setStaticTotalBudget(double staticTotalBudget) {
        this.staticTotalBudget = staticTotalBudget;
    }

    public double getStaticBillsBudget() {
        return staticBillsBudget;
    }

    public void setStaticBillsBudget(double staticBillsBudget) {
        this.staticBillsBudget = staticBillsBudget;
    }

    public double getStaticFoodBudget() {
        return staticFoodBudget;
    }

    public void setStaticFoodBudget(double staticFoodBudget) {
        this.staticFoodBudget = staticFoodBudget;
    }

    public double getStaticEntertainmentBudget() {
        return staticEntertainmentBudget;
    }

    public void setStaticEntertainmentBudget(double staticEntertainmentBudget) {
        this.staticEntertainmentBudget = staticEntertainmentBudget;
    }

    public double getStaticOtherBudget() {
        return staticOtherBudget;
    }

    public void setStaticOtherBudget(double staticOtherBudget) {
        this.staticOtherBudget = staticOtherBudget;
    }

}// Account
