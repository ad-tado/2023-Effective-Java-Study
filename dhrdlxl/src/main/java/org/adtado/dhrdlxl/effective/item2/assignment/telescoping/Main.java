package org.adtado.dhrdlxl.effective.item2.assignment.telescoping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    private static NutritionFacts nutritionFacts1;
    private static NutritionFacts nutritionFacts2;

    public static void main(String[] args) throws InterruptedException {
        // Telescoping(점층적 생성자 패턴)
        // 1. 장점: 멀티 스레드 환경에서 안정적이다.
        // 2. 단점: 매개변수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.

        Thread thread1 = new Thread(() -> {
            nutritionFacts1 = new NutritionFacts(1, 2, 3, 4, 5, 6);
        });

        Thread thread2 = new Thread(() -> {
            nutritionFacts2 = new NutritionFacts(1, 2, 3, 4, 5, 6);
        });

        thread1.start();
        thread2.start();

        Thread.sleep(100);

        log.info("Object equal?: " + nutritionFacts1.equals(nutritionFacts2)); // true
    }
}
