package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="products")
@NamedQueries({
        @NamedQuery(name = "findAllProduct", query = "select p from Product p JOIN FETCH p.category"),
        @NamedQuery(name = "findProductById", query = "from Product c where c.id = :id"),
        @NamedQuery(name = "findProductByCategory", query = "from Product c where c.category.id = :idcategory"),
        @NamedQuery(name = "countAllProduct", query = "select count(*) from Product "),
        @NamedQuery(name = "deleteByIdProduct", query = "delete from Product c where c.id = :id")

})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="category_id", nullable=false)
    private Category category;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private BigDecimal price;

    public Product() {
    }

    public Product(Long id, Category category, String name, String description, BigDecimal price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object other) {
        return (other instanceof Product) && (id != null)
                ? id.equals(((Product) other).id)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Product[%d, %s]", id, name);
    }

}
