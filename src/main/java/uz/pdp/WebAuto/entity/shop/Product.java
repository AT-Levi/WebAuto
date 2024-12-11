package uz.pdp.WebAuto.entity.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private String description;
    private int count;
    private String category;
    private String createDate;
    private String imageBase64;
    private String imageUrl;

    public Product(int id, String description, int price) {
        this.id = id;
        this.name = description;
        this.price = price;
    }

}


