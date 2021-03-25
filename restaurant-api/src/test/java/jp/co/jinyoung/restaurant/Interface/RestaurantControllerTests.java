package jp.co.jinyoung.restaurant.Interface;

import jp.co.jinyoung.restaurant.application.RestaurantService;
import jp.co.jinyoung.restaurant.domain.MenuItem;
import jp.co.jinyoung.restaurant.domain.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    //2020-12-31 金（珍) 追加　レストラン店舗リストのテストコード
    @Test
    public void list() throws Exception {
        //Mock Object
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Jinyoung Kim")
                .address("Chiba")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Jinyoung Kim\"")
                ));
    }

    //2020-12-31 金(珍) レストランdetailテストコード
    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L)
                .name("Chiba Kim")
                .address("Tokyo")
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("Sushi")
                .build();

        restaurant1.setMenuItems(Arrays.asList(menuItem));

        Restaurant restaurant2 = Restaurant.builder()
                .id(2021L)
                .name("Cyber Food")
                .address("Chiba")
                .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2021L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Chiba Kim\"")
                ))
        .andExpect(content().string(
                containsString("Sushi")
        ));

        mvc.perform(get("/restaurants/2021"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2021")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }


    @Test
    public void createWithValidData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invoction -> {
            Restaurant restaurant = invoction.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });


        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Samgyopsal\", \"address\":\"matsudo\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void createWithInvalidData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invoction -> {
            Restaurant restaurant = invoction.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\", \"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jinyoung Bar\", \"address\":\"ibaraki\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L, "Jinyoung Bar", "ibaraki");
    }
}