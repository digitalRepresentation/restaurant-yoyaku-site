package jp.co.jinyoung.restaurant.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
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
    @Setter
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient
    private List<MenuItem> menuItems;


    public String getInforamation() {
        return name + " in " + address;
    }


    public void setMenuItems(List<MenuItem> menuItems) {
            this.menuItems = new ArrayList<>(menuItems);
    }


    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
