/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client;

import entities.Products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.NoResultException;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@ViewScoped
public class OrderBean implements Serializable {

    @EJB
    private ProductModel productModel;

    int pid;
    Products currentProduct;

    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
    }

    public List<Products> getListProductSelectBox() {
        List<Products> products = productModel.getAllForSelectBox();
        return products;
    }

    public void getProduct(AjaxBehaviorEvent ev) {
        currentProduct = productModel.getById(pid);
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Products getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Products currentProduct) {
        this.currentProduct = currentProduct;
    }

}
