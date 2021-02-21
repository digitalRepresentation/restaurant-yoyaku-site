package jp.co.jinyoung.restaurant.Interface;


import jp.co.jinyoung.restaurant.application.RestaurantService;
import jp.co.jinyoung.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    //2020-12-30 金(珍) 追加　レストラン店舗リスト
    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    //2020-12-30 金(珍) 追加　レストラン店舗リスト詳細
    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        //基本情報+メニュー情報
        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }
}
