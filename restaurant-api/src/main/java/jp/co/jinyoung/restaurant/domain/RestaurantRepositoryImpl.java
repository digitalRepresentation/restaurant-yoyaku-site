package jp.co.jinyoung.restaurant.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl() {
        restaurants.add(new Restaurant(1004L, "Bob zip", "Chiba"));
        restaurants.add(new Restaurant(2021L, "Cyber Food", "Chiba"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream()
        .filter(r -> r.getId().equals(id))
        .findFirst()
        .orElse(null);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234);
        restaurants.add(restaurant);
        return restaurant;
    }
}
