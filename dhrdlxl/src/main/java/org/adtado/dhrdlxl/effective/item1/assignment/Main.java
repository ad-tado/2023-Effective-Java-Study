package org.adtado.dhrdlxl.effective.item1.assignment;

import org.adtado.dhrdlxl.effective.item1.assignment.domain.User;
import org.adtado.dhrdlxl.effective.item1.assignment.entity.UserEntity;

public class Main {
    public static void main(String[] args) {
        User user = new User("name", 11L, "address");

        // 1. 정적 팩터리 메서드 valueOf() 사용하는 경우
        // 의미가 명확하게 전달된다.
        UserEntity userEntity1 = UserEntity.valueOf(user);

        // 2. 생성자를 사용하는 경우
        // 의미가 잘 전달되지 않는다.
        UserEntity userEntity2 = new UserEntity(user.getName(), user.getAge(), user.getAddress());
    }
}
