package org.adtado.verticalcode.effective.chap2.item2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutritionFact {
    private int servingSize;
    private int servings;
    private int calories;
    private int fat;
    private int protein;
    private int carbohydrate;
    private int sodium;

    public NutritionFact() {}

    public NutritionFact(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFact(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFact(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFact(int servingSize, int servings, int calories, int fat, int protein) {
        this(servingSize, servings, calories, fat, protein, 0);
    }

    public NutritionFact(int servingSize, int servings, int calories, int fat, int protein, int carbohydrate) {
        this(servingSize, servings, calories, fat, protein, carbohydrate, 0);
    }

    public NutritionFact(int servingSize, int servings, int calories, int fat, int protein, int carbohydrate, int sodium) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.sodium = sodium;
    }

    public static class Builder2 {
        private final int servingSize;
        private final int servings;

        private int calories = 0;
        private int fat = 0;
        private int protein = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder2(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder2 calories(int val) {
            calories = val;
            return this;
        }

        public Builder2 fat(int val) {
            fat = val;
            return this;
        }

        public Builder2 protein(int val) {
            protein = val;
            return this;
        }

        public Builder2 carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder2 sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFact build() {
            return new NutritionFact(this);
        }
    }

    private NutritionFact(Builder2 builder2) {
        servingSize = builder2.servingSize;
        servings = builder2.servings;
        calories = builder2.calories;
        fat = builder2.fat;
        protein = builder2.protein;
        carbohydrate = builder2.carbohydrate;
        sodium = builder2.sodium;
    }
}
