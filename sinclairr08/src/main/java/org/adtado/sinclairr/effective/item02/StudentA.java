package org.adtado.sinclairr.effective.item02;

public class StudentA {
    private final String name;      // 필수
    private final int grade;        // 필수
    private String description;
    private int level;
    private int power;
    private int balance;

    public StudentA(String name, int grade) {
        this(name, grade, "", 0, 0, 0);
    }
    public StudentA(String name, int grade, String description) {
        this(name, grade, description, 0, 0, 0);
    }
    public StudentA(String name, int grade, String description, int level) {
        this(name, grade, description, level, 0, 0);
    }
    public StudentA(String name, int grade, String description, int level, int power) {
        this(name, grade, description, level, power, 0);
    }
    public StudentA(String name, int grade, String description, int level, int power, int balance) {
        this.name = name;
        this.grade = grade;
        this.description = description;
        this.level = level;
        this.power = power;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void addMoney(int money) {
        this.balance += money;
    }
}
