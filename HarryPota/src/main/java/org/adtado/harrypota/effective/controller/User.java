package org.adtado.harrypota.effective.controller;

import ch.qos.logback.core.net.SyslogOutputStream;

public class User {
    //ITEM 01의 요지는 조금 특별한 의미를 가진 use case가 존재하는 인스턴스를 생성할 경우에 정적 팩터리 매서드를 활용하라는 것입니다.
    // 굳이 생성자를 안 쓰고 일부러 정적 팩터리로 대체할 필요가 없다는 것이죠.
    //
    //1. 생성자 대신 정적 팩터리를 사용한 예를 들어주시고, 예를 든 이유를 설명해주세요.
    //2. 생성자로 바꿀 수 있다면, 생성자로도 바꿔주세요.
    private final String name;
    private final int age;

    public static User inforation(String name, int age) {
        return new User(name, age);
    };

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        // 정적 팩터리 메서드 사용
        User user = inforation("harry", 27);

        // 생성자 사용
        User user1 = new User("Pota", 28);

        System.out.println("이름 : " + user.name + "\n" + "나이 : " + user.age);
        System.out.println("이름 : " + user1.name + "\n" + "나이 : " + user1.age);
    }
}
