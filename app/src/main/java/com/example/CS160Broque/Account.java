package com.example.CS160Broque;

import android.os.Parcel;
import android.os.Parcelable;

// This class creates an Account Object using data retrieved from the Database
// Newly created accounts will be initialized with 0 values.
public class Account implements Parcelable {
    private String fullName;
    private String userName;
    private String password;
    private String phoneNumber;

    private double totalBudget;
    private double billsBudget;
    private double foodBudget;
    private double entertainmentBudget;
    private double otherBudget;

    private double totalExpense;
    private double billsExpense;
    private double foodExpense;
    private double entertainmentExpense;
    private double otherExpense;

    private double monthlyIncome;

    public Account(){
        this.fullName = "";
        this.userName = "";
        this.password = "";
        this.phoneNumber = "";

        this.totalBudget = 0;
        this.billsBudget = 0;
        this.foodBudget = 0;
        this.entertainmentBudget = 0;
        this.otherBudget = 0;

        this.totalExpense = 0;
        this.billsExpense = 0;
        this.foodExpense = 0;
        this.entertainmentExpense = 0;
        this.otherExpense = 0;
    }// Account Default Constructor

    public Account(String fullName, String userName, String password, String phoneNumber){
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;

        this.totalBudget = 0;
        this.billsBudget = 0;
        this.foodBudget = 0;
        this.entertainmentBudget = 0;
        this.otherBudget = 0;

        this.totalExpense = 0;
        this.billsExpense = 0;
        this.foodExpense = 0;
        this.entertainmentExpense = 0;
        this.otherExpense = 0;
    }// Account Constructor

    protected Account(Parcel in) {
        fullName = in.readString();
        userName = in.readString();
        password = in.readString();
        phoneNumber = in.readString();

        totalBudget = in.readDouble();
        billsBudget = in.readDouble();
        foodBudget = in.readDouble();
        entertainmentBudget = in.readDouble();
        otherBudget = in.readDouble();

    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    // Getters and Setters

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

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

    public String getTotalBudget() {
        return Double.toString(totalBudget);
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public double getBillsBudget() {
        return billsBudget;
    }

    public void setBillsBudget(double billsBudget) {
        this.billsBudget = billsBudget;
    }

    public double getFoodBudget() {
        return foodBudget;
    }

    public void setFoodBudget(double foodBudget) {
        this.foodBudget = foodBudget;
    }

    public double getEntertainmentBudget() {
        return entertainmentBudget;
    }

    public void setEntertainmentBudget(double entertainmentBudget) {
        this.entertainmentBudget = entertainmentBudget;
    }

    public double getOtherBudget() {
        return otherBudget;
    }

    public void setOtherBudget(double otherBudget) {
        this.otherBudget = otherBudget;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getBillsExpense() {
        return billsExpense;
    }

    public void setBillsExpense(double billsExpense) {
        this.billsExpense = billsExpense;
    }

    public double addBillsExpense(double amt) {
        this.billsExpense = billsExpense + amt;
        return billsExpense;
    }

    public double getFoodExpense() {
        return foodExpense;
    }

    public void setFoodExpense(double foodExpense) {
        this.foodExpense = foodExpense;
    }

    public double addFoodExpense(double amt) {
        this.foodExpense = foodExpense + amt;
        return foodExpense;
    }

    public double getEntertainmentExpense() {
        return entertainmentExpense;
    }

    public void setEntertainmentExpense(double entertainmentExpense) {
        this.entertainmentExpense = entertainmentExpense;
    }

    public double addEntertainmentExpense(double amt) {
        this.entertainmentExpense = entertainmentExpense + amt;
        return entertainmentExpense;
    }

    public double getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(double otherExpense) {
        this.otherExpense = otherExpense;
    }

    public double addOtherExpense(double amt) {
        this.otherExpense = otherExpense + amt;
        return otherExpense;
    }

    @Override
    public String toString() {
        return "Account{" +
                "fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", totalBudget=" + totalBudget +
                ", billsBudget=" + billsBudget +
                ", foodBudget=" + foodBudget +
                ", entertainmentBudget=" + entertainmentBudget +
                ", otherBudget=" + otherBudget +
                ", totalExpense=" + totalExpense +
                ", billsExpense=" + billsExpense +
                ", foodExpense=" + foodExpense +
                ", entertainmentExpense=" + entertainmentExpense +
                ", otherExpense=" + otherExpense +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(totalBudget);
        parcel.writeDouble(billsBudget);
        parcel.writeDouble(foodBudget);
        parcel.writeDouble(entertainmentBudget);
        parcel.writeDouble(otherBudget);

        parcel.writeString(fullName);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeString(phoneNumber);
    }

    // Updates budgets
    public void insertBudgets(double bills, double food, double entertainment, double other) {
        this.totalBudget = bills + food + entertainment + other;
        this.billsBudget = bills;
        this.foodBudget = food;
        this.entertainmentBudget = entertainment;
        this.otherBudget = other;
    }

    public void insertExpenses(double bills, double food, double entertainment, double other) {
        this.totalExpense = bills + food + entertainment + other;
        this.billsExpense = bills;
        this.foodExpense = food;
        this.entertainmentExpense = entertainment;
        this.otherExpense = other;
    }

}// Account
