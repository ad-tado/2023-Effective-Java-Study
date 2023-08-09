package org.adtado.dhrdlxl.effective.item1.assignment.entity;

import org.adtado.dhrdlxl.effective.item1.assignment.domain.User;

public class UserEntity {
    String name;
    Long age;
    String address;

    public UserEntity(String name, Long age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public static UserEntity valueOf(User user) {
        return new UserEntity(user.getName(), user.getAge(), user.getAddress());
    }
}
