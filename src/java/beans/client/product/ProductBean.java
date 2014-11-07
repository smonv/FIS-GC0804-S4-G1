/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.product;

import entities.Products;
import helpers.ApplicationHelper;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.ProductModel;

/**
 *
 * @author Cu Beo
 */
@ManagedBean
@ViewScoped
public class ProductBean {

    @EJB
    private ProductModel productModel;

    private int pid;
    private int pageSize;
    private Products currentProduct;
    Map<String, Object> session;
    private int quantity;
    private String mode;
    private boolean disable = false;
    private List<Products> list;
    private int page; 
    
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
    public boolean isHasNextPage() {
            return (page + 1) * this.getPageSize() + 1<= productModel.getAll().size();
    }
   
    public List<Products> getAllProduct() {
            loadProductList();
        return list;
    }
    
    public List<Products> getTop12Product() {
        return productModel.getTop12();
    }
    
    public List<Products > getProductsByCtegory(int id) {
        list = productModel.getProductsByCategory(id);
        return list;
    }
    
    public void loadProductList(){
        list = productModel.getAllProduct(this.getStartIndex(), this.getPageSize());    
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

    public int getStartIndex() {
        return page * pageSize;
    }


    public int getPageSize() {
        return pageSize = 2;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    } 

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    

}
