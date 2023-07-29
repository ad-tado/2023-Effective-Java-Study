package org.adtado.verticalcode.effective.chap2.item1;

import java.util.Objects;

public class Item1 {

    //정적 팩토리 메서드 예시 - Optional
    //자바 8부터 도입된 NPE를 방지할 수 있는 + 누구나 쓰고 있는 필수 로직
    public static class myOptional<T> {
        private static final myOptional<?> EMPTY = new myOptional<>(null);

        private final T value;

        //생성자
        public myOptional(T value) {
            this.value = value;
        }

        //정적 팩토리 메서드
        public <T> myOptional<T> of(T value) {
            return new myOptional<>(Objects.requireNonNull(value));
        }


    }

}
