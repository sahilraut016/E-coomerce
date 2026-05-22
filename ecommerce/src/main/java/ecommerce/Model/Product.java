package ecommerce.Model;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private double price;
    private boolean sale;

    public Product() {}

    public Product(Integer id, String name, String description, double price, boolean sale) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sale = sale;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isSale() { return sale; }
    public void setSale(boolean sale) { this.sale = sale; }
}