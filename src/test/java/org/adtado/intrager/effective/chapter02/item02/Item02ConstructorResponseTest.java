package org.adtado.intrager.effective.chapter02.item02;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Item02ConstructorResponseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Item02TelescopingResponse telescopingResponse;
    private Item02JavaBeansResponse javaBeansResponse;
    private Item02BuilderResponse builderResponse;

    private int servingSize = 240;
    private int servings = 8;
    private int calories = 100;
    private int fat = 0;
    private int sodium = 35;
    private int carbohydrate = 27;

    private class TelescopeThread extends Thread {
        @Override
        public void run() {
            telescopingResponse = new Item02TelescopingResponse(servingSize, servings, calories, fat, sodium, carbohydrate);
        }
    }

    private class JavaBeansThread extends Thread {
        @Override
        public void run() {
            javaBeansResponse = new Item02JavaBeansResponse();
            javaBeansResponse.setServingSize(servingSize);
            javaBeansResponse.setServings(servings);
            javaBeansResponse.setCalories(calories);
            javaBeansResponse.setFat(fat);
            javaBeansResponse.setSodium(sodium);
            javaBeansResponse.setCarbohydrate(carbohydrate);
        }
    }

    private class BuilderThread extends Thread {
        @Override
        public void run() {
            builderResponse = new Item02BuilderResponse.Builder(servingSize, servings, calories, fat, sodium, carbohydrate).build();
        }
    }

    @DisplayName("멀티스레드에서 telescope, javaBeans, builder 패턴을 테스트한다")
    @Test
    void runThread() {
        Thread telescope = new TelescopeThread();
        Thread javaBeans = new JavaBeansThread();
        Thread builder = new BuilderThread();

        telescope.start();
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);
        logger.info("telescope : {}", telescopingResponse);

        javaBeans.start();
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);
        logger.info("javaBeans : {}", javaBeansResponse);

        builder.start();
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
        logger.info("builder : {}", builderResponse);
    }
}
