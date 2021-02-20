package jp.co.jinyoung.restaurant.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    List<MenuItem> menuItems = new ArrayList<>();

    MenuItemRepositoryImpl() {
        menuItems.add(new MenuItem("Sushi"));
    }
    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
        return menuItems;
    }
}
