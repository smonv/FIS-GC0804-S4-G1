/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client;

import entities.Products;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@RequestScoped
public class ProductBean {

    ProductModel pm;

    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
    }

    public List<Products> listAllProduct() {
        pm = new ProductModel();
        return pm.fetchAll();
    }

}
