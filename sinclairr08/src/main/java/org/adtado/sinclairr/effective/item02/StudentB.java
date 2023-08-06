package org.adtado.sinclairr.effective.item02;

public class StudentB {
    private String name = "INVALID_NAME";      // 필수
    private int grade = -1;        // 필수
    private String description = "";
    private int level = 0;
    private int power = 0;
    private int balance = 0;

    public StudentB() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void addMoney(int money) {
        this.balance += money;
    }
}
