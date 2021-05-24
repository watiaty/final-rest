package by.epam.shop.dto;

public class CategoryDTO {
    private Integer id;
    private String name;
    private CategoryDTO parent;
    private CategoryDTO child;

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO() {
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

    public CategoryDTO getParent() {
        return parent;
    }

    public void setParent(CategoryDTO parent) {
        this.parent = parent;
    }

    public CategoryDTO getChild() {
        return child;
    }

    public void setChild(CategoryDTO child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return name + " ; " + parent.getName() + " ; " + child.getName();
    }
}
