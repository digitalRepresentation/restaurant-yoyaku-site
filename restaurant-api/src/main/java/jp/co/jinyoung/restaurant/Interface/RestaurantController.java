package jp.co.jinyoung.restaurant.Interface;


import jp.co.jinyoung.restaurant.domain.Restaurant;
import jp.co.jinyoung.restaurant.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    //2020-12-30 金(珍) 追加　レストラン店舗リスト
    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = repository.findAll();

        return restaurants;
    }

    //2020-12-30 金(珍) 追加　レストラン店舗リスト詳細
    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        //List<Restaurant> restaurants = repository.findAll();
        //探索するレストランのお店が多い場合
        Restaurant restaurant = repository.findById(id);

        return restaurant;
    }
}
