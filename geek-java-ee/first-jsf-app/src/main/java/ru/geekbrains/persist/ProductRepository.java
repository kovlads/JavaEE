package ru.geekbrains.persist;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;


    @PostConstruct
    public void init() throws Exception {
/*
        if (countAll() == 0) {
            try {
                ut.begin();
                Category category = em.find(Category.class, "Books");

                Product product = new Product(null, category, "Product  1",
                        "Description of product 1", new BigDecimal(100));

                saveOrUpdate(product);
                category.getProducts().add(product);

                product = new Product(null, category, "Product  2",
                        "Description of product 2", new BigDecimal(200));

                saveOrUpdate(product);
                category.getProducts().add(product);

                product = new Product(null, category, "Product  3",
                        "Description of product 3", new BigDecimal(300));

                saveOrUpdate(product);
                category.getProducts().add(product);

                ut.commit();
            } catch (Exception ex) {
                logger.error("", ex);
                ut.rollback();
            }
        }

*/
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProduct", Product.class)
                .getResultList();
    }

    public List<Product> findByCategory(Category category) {
        if (category == null) {
            return findAll();
        } else {
            return em.createNamedQuery("findProductByCategory", Product.class)
                    .setParameter("idcategory", category.getId())
                    .getResultList();
        }
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProduct", Long.class)
                .getSingleResult();
    }

    public Product findById(Long id) {
        return em.createNamedQuery("findProductById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteByIdProduct")
                .setParameter("id", id)
                .executeUpdate();
    }
}
