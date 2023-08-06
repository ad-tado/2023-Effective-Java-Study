package org.adtado.dhrdlxl.effective.item2.assignment.builder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    private static NutritionFacts nutritionFacts1;
    private static NutritionFacts nutritionFacts2;

    public static void main(String[] args) throws InterruptedException {
        // Builder
        // 1. 장점: 멀티 스레드 환경에서 안정적이다, 매개변수가 많아도 코드 작성 및 읽기가 쉽다.
        // 2. 단점: 빌더 생성비용이 크지는 않으나 성능에 민감한 상황에서는 문제가 될 수 있다.

        Thread thread1 = new Thread(() -> {
            nutritionFacts1 = NutritionFacts.builder()
                    .servingSize(1)
                    .servings(2)
                    .calories(3)
                    .fat(4)
                    .sodium(5)
                    .carbohydrate(6)
                    .build();
        });

        Thread thread2 = new Thread(() -> {
            nutritionFacts2 = NutritionFacts.builder()
                    .servingSize(1)
                    .servings(2)
                    .calories(3)
                    .fat(4)
                    .sodium(5)
                    .carbohydrate(6)
                    .build();
        });

        thread1.start();
        thread2.start();

        Thread.sleep(100);

        log.info("Object equal?: " + nutritionFacts1.equals(nutritionFacts2)); // true
    }
}
