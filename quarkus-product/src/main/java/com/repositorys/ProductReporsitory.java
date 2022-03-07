package com.repositorys;

import com.entitys.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductReporsitory {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createProduct(Product p){
        entityManager.persist(p);
    }

    @Transactional
    public void deleteProduct(Product p){
        entityManager.remove(p);
    }

    @Transactional
    public Product findById(Long id){
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void update(Product p){
        entityManager.merge(p);
    }

    @Transactional
    public List<Product> listProduct(){
        List<Product> products = entityManager.createQuery("select p from Product p").getResultList();
        return products;
    }
}
