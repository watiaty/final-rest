package by.epam.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parent")
    private Category parent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_child")
    private Category child;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Category getChild() {
        return child;
    }

    public void setChild(Category child) {
        this.child = child;
    }
}
