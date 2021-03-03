package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class ProductController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Inject
    private ProductRepository productRepository;

    private Product product;

    private Category category;

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        logger.info("select category: " + category);
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public List<Product> getAllProducts() {
        if (category == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findByCategory(category);
        }
    }


    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect-true";
    }

    public void deleteProduct(Product product) {
        logger.info("deleting product: " + product.getId());
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect-true";
    }


    public void onSelectCategory(ValueChangeEvent event) {
        logger.info("onSelectCategory: " + event.toString());

    }


/*
    public void onSelectCategory(SelectEvent<Category> event) {
        logger.info("onSelectCategory: " + event.toString());
        category = (Category) event.getObject();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().getName()));
    }
*/

}
