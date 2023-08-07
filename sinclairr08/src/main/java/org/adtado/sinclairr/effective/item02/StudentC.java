package org.adtado.sinclairr.effective.item02;

public class StudentC {
    private final String name;      // 필수
    private final int grade;        // 필수
    private String description;
    private int level;
    private int power;
    private int balance;

    public static class StudentCBuilder {
        private final String name;      // 필수
        private final int grade;        // 필수
        private String description = "";
        private int level = 0;
        private int power = 0;
        private int balance = 0;

        public StudentCBuilder(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public StudentCBuilder description(String val) {
            this.description = val;
            return this;
        }

        public StudentCBuilder level(int val) {
            this.level = val;
            return this;
        }

        public StudentCBuilder power(int val) {
            this.power = val;
            return this;
        }

        public StudentCBuilder balance(int val) {
            this.balance = val;
            return this;
        }

        public StudentC build() {
            return new StudentC(this);
        }
    }

    private StudentC(StudentCBuilder builder) {
        this.name = builder.name;
        this.grade = builder.grade;
        this.description = builder.description;
        this.level = builder.level;
        this.power = builder.power;
        this.balance = builder.balance;
    }

    public int getBalance() {
        return balance;
    }

    public void addMoney(int money) {
        this.balance += money;
    }
}
