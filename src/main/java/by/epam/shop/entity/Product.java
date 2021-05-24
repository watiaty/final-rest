package by.epam.shop.entity;

import by.epam.shop.model.Price;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_categories",
            joinColumns = {@JoinColumn(name = "id_product")},
            inverseJoinColumns = {@JoinColumn(name = "id_category")}
    )
    private List<Category> categories;

    transient private List<Price> prices;

    public Product() {
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Price> getPrice() {
        return prices;
    }

    public void setPrice(List<Price> prices) {
        this.prices = prices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
