package jp.co.jinyoung.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

//    private String regionName; // Tokyo
//    private String categoryName; // Korean
//    private String tagNames; // #JMT

    @Transient
    private List<MenuItem> menuItems;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    public String getInforamation() {
        return name + " in " + address;
    }


    public void addMenuItem(MenuItem menuItem) {
        if(menuItem == null) {
            menuItems = new ArrayList<>();
        }
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {

        for (MenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }


    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
