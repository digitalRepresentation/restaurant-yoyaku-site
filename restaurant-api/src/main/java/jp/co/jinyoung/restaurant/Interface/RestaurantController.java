package jp.co.jinyoung.restaurant.Interface;


import jp.co.jinyoung.restaurant.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    //2020-12-30 金(珍) 追加　レストラン店舗リスト
    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Chiba");

        restaurants.add(restaurant);
        return restaurants;
    }

    //2020-12-30 金(珍) 追加　レストラン店舗リスト詳細
    @GetMapping("/restaurants/1")
    public Restaurant detail() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Chiba");
        return restaurant;
    }
}
