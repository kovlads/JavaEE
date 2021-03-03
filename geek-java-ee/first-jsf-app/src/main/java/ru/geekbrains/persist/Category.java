package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
@NamedQueries({
        @NamedQuery(name = "findAllCategory", query = "from Category"),
        @NamedQuery(name = "findCategoryById", query = "from Category c  where c.id = :id"),
        @NamedQuery(name = "countAllCategory", query = "select count(*) from Category "),
        @NamedQuery(name = "deleteByIdCategory", query = "delete from Category c where c.id = :id")

})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<Product> products = new ArrayList<Product>();

    public Category() {
    }


    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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

    @Override
    public boolean equals(Object other) {
        return (other instanceof Category) && (id != null)
                ? id.equals(((Category) other).id)
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
        return String.format("Category[%d, %s]", id, name);
    }

}
