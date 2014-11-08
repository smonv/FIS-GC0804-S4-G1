package beans.client.product;

import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.CategoryModel;
import models.ProductModel;

@ManagedBean
@ViewScoped
public class ProductBean {

    @EJB
    private CategoryModel categoryModel;

    @EJB
    private ProductModel productModel;

    private int pid;
    private int pageSize = 1;
    private Products currentProduct;
    Map<String, Object> session;
    private int quantity;
    private String mode;
    private boolean disable = false;
    private List<Products> products;
    private int page;
    private int cid;
    private String category;
    private int totalProducts;

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
        return (page + 1) * this.getPageSize() + 1 <= productModel.getAll().size();
    }

    public List<Products> getTop12Product() {
        return productModel.getTop12();
    }

    public List<Products> getProducts() {
        loadProductList();
        return products;
    }

    public void loadProductList() {
        if (cid == 0) {
            products = productModel.getAllProduct(this.getStartIndex(), this.getPageSize());
            totalProducts = productModel.getAllTotalProducts();
        } else {
            products = productModel.getAll(this.getStartIndex(), this.getPageSize(), cid);
            totalProducts = productModel.getAllTotalProducts(cid);
        }
    }

    public void clearParamAndRedirect() {
        ApplicationHelper.redirect("/product/index.xhtml", true);
    }

    public void clearPageAndRedirect() {
        String cat_name = ApplicationHelper.getRequestParameterMap().get("cat_name");
        String scid = ApplicationHelper.getRequestParameterMap().get("scid");
        ApplicationHelper.redirect("/product/index.xhtml?category=" + cat_name + "&cid=" + scid, true);
    }

    ///SET GET
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
        return pageSize;
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

}
