package by.epam.shop.model;

public class Price {
    private Integer id;
    private String currency;
    private Integer value;

    public Price(Integer id, String currency, Integer value) {
        this.id = id;
        this.currency = currency;
        this.value = value;
    }

    public Price() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
