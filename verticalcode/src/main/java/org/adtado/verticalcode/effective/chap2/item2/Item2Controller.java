package org.adtado.verticalcode.effective.chap2.item2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Tag(name = "CHAP 2", description = "test")
public class Item2Controller {

    @GetMapping("/api/v1/test/item2")
    @Operation(summary = "패턴 별 속도 측정")
    public void runSpeedTest() {
        // 객체를 생성하는 속도를 비교해본다

        long begin = System.currentTimeMillis();

        int i = 0;
        while(i < 300000) {
            NutritionFact nutritionFact = new NutritionFact(i, i);
            i++;
        }
        long term = System.currentTimeMillis() - begin;
        log.info("constructor pattern: " + term + "ms");

        long begin2 = System.currentTimeMillis();
        i = 0;
        while(i < 300000) {
            NutritionFact nutritionFact2 = new NutritionFact();
            nutritionFact2.setServingSize(i);
            nutritionFact2.setServings(i);
            i++;
        }
        long term2 = System.currentTimeMillis() - begin2;
        log.info("java beans pattern: " + term2 + "ms");


        long begin3 = System.currentTimeMillis();
        i = 0;
        while(i < 300000) {
            NutritionFact nutritionFact2 = new NutritionFact.Builder2(i, i).build();
            i++;
        }
        long term3 = System.currentTimeMillis() - begin3;
        log.info("builder pattern: " + term3 + "ms");

        //결과 : 12, 3, 21
    }
}
