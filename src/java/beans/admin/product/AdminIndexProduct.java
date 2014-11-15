package beans.admin.product;

import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class AdminIndexProduct {

    @EJB
    private ProductModel productModel;

    //bean variables
    private List<Products> products; //list product for show
    private int pageSize = 10; //page size for pagination;
    private int currentPage = 1; //current page value for pagination
    private long totalProduct = 0;
    private long totalPage;
    //view params
    private String page; //page value get from param;

    public AdminIndexProduct() {
    }

    public void init() { //call when prerender view
        currentPage = ApplicationHelper.isInteger(page) ? Integer.parseInt(page) : 1; //check if page is number, convert page to integer, else current page = 1;
        //if dont have other variable like category, manufactorer, min load, max load
        //set category and manufactorer is null, min load = 0, max load = 2000
        products = productModel.getAll(currentPage - 1, pageSize, null, 0, 2000, null); //get all product for show in datatable;
        totalProduct = productModel.countAll(null, 0, 2000, null); //count all product, set like get all function;
        if (totalProduct % pageSize != 0) {
            totalPage = totalProduct % pageSize + 1;
        } else {
            totalPage = totalProduct % pageSize;
        }

    }

    ///SET GET
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(long totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

}
