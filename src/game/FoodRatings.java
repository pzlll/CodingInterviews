package game;

import java.util.*;

//第 303 场周赛
public class FoodRatings {
    class FoodToRate {
        private String food;
        private Integer rate;
        private String cuisine;
        public FoodToRate(String food, Integer rate,String cuisine) {
            this.food = food;
            this.rate = rate;
            this.cuisine = cuisine;
        }


    }
    private Map<String, TreeSet<FoodToRate>> cuisinesToFood;
    private Map<String, FoodToRate> foodToObject;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        cuisinesToFood = new HashMap<>();
        foodToObject = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            TreeSet<FoodToRate> orDefault = cuisinesToFood.getOrDefault(cuisines[i], new TreeSet<>(new Comparator<FoodToRate>() {
                @Override
                public int compare(FoodToRate o1, FoodToRate o2) {
                    // Integer类型比较 要么调用Integer.intValue()转为基本类型用“==”比较，要么直接用equals比较。
                    if(o1.rate.equals(o2.rate)) {
                        return o1.food.compareTo(o2.food);
                    }
                    return o2.rate - o1.rate;
                }
            }));
            FoodToRate ftr = new FoodToRate(foods[i], ratings[i], cuisines[i]);
            orDefault.add(ftr);
            foodToObject.put(foods[i], ftr);
            cuisinesToFood.put(cuisines[i], orDefault);
        }
    }

    public void changeRating(String food, int newRating) {
        FoodToRate foodToRate = foodToObject.get(food);
        String c = foodToRate.cuisine;
        TreeSet<FoodToRate> foodToRates = cuisinesToFood.get(foodToRate.cuisine);
        foodToRates.remove(foodToRate);
        FoodToRate f = new FoodToRate(food, newRating, c);
        foodToRates.add(f);
        foodToObject.put(food, f);

    }

    public String highestRated(String cuisine) {
        TreeSet<FoodToRate> foodToRates = cuisinesToFood.get(cuisine);
        FoodToRate first = foodToRates.first();

        return first.food;

//        int rate = 0;
//        for (String s :
//                strings) {
//            rate = Math.max(rate, foodToRate.get(s));
//        }
//
//        String res = null;
//
//        for (String s :
//                strings) {
//            if (rate == foodToRate.get(s)) {
//               if(res == null) {
//                   res = s;
//               }else{
//                   if(s.compareTo(res) < 0) {
//                       res = s;
//                   }
//               }
//            }
//        }
//
//        return res;
    }
}
