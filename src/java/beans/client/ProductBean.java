/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client;

import entities.Products;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private ProductModel productModel;

    public ProductBean() {
    }

    public List<Products> getAllProduct() {
        return productModel.getAll();
    }

}
