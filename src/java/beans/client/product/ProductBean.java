/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.product;

import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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

    private int pid;
    private Products currentProduct;
    Map<String, Object> session;
    private int quantity;
    private String mode;

    public ProductBean() {
    }

    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (!productModel.productExists(pid)) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
            checkMode();
        }
        currentProduct = productModel.getById(pid);
    }

    public void checkMode() {
        if (mode != null) {
            if (!mode.equals("update")) {
                ApplicationHelper.redirect("/404.xhtml", false);
            }
        }
    }

    public List<Products> getAllProduct() {
        return productModel.getAll();
    }

    public List<Products> getTop12Product() {
        return productModel.getTop12();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
