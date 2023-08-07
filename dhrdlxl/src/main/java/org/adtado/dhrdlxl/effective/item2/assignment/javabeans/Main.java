package org.adtado.dhrdlxl.effective.item2.assignment.javabeans;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    private static NutritionFacts nutritionFacts1;
    private static NutritionFacts nutritionFacts2;

    public static void main(String[] args) throws InterruptedException {
        // Javabeans
        // 1. 장점: 코드 가독성이 좋다.
        // 2. 단점: 여러 개의 메서드를 호출해야하며, 멀티 스레드 환경에서 문제가 발생할 수 있다.

        Thread thread1 = new Thread(() -> {
            nutritionFacts1 = new NutritionFacts();
            nutritionFacts1.setServings(1);
            nutritionFacts1.setServingSize(2);
            nutritionFacts1.setCalories(3);
            nutritionFacts1.setFat(4);
            nutritionFacts1.setSodium(5);
            nutritionFacts1.setCarbohydrate(6);
        });

        Thread thread2 = new Thread(() -> {
            nutritionFacts2 = new NutritionFacts();
            nutritionFacts2.setServings(1);
            nutritionFacts2.setServingSize(2);
            nutritionFacts2.setCalories(3);
            nutritionFacts2.setFat(4);
            nutritionFacts2.setSodium(5);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nutritionFacts2.setCarbohydrate(6);
        });

        thread1.start();
        thread2.start();

        Thread.sleep(100);

        log.info("Object equal?: " + nutritionFacts1.equals(nutritionFacts2)); // false
    }
}
