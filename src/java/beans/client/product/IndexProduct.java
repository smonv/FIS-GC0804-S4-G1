package beans.client.product;

import entities.Categories;
import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.CategoryModel;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class IndexProduct {

    @EJB
    private CategoryModel categoryModel;

    @EJB
    private ProductModel productModel;

    //bean variables
    private List<Products> products;
    private int current_page = 1;
    private int pageSize = 6;
    private long totalProduct;
    private long totalPage;
    private int current_cid = 0;
    private int currentMinLoad = 0;
    private int currentMaxLoad = 2000;
    //view param
    private String page;
    private String cid;
    private String minLoad;
    private String maxLoad;

    public IndexProduct() {
    }

    public void init() {

        current_cid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0; //check cid
        Categories category = categoryModel.getById(current_cid); //get category by cid;

        currentMinLoad = ApplicationHelper.isInteger(minLoad) ? Integer.parseInt(minLoad) : 0; //check min load
        currentMaxLoad = ApplicationHelper.isInteger(maxLoad) ? Integer.parseInt(maxLoad) : 2000; //check max load

        current_page = ApplicationHelper.getCurrentPage(page);
        products = productModel.getAll(current_page - 1, pageSize, category, currentMinLoad, currentMaxLoad);
        totalProduct = productModel.countAll(category, currentMinLoad, currentMaxLoad);

    }

    public List<Categories> getAllCategories() {
        return categoryModel.getAllCategory();
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public long getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(long totalProduct) {
        this.totalProduct = totalProduct;
    }

    public long getTotalPage() {
        if (totalProduct % pageSize != 0) {
            totalPage = totalProduct / pageSize + 1;
        } else {
            totalPage = totalProduct / pageSize;
        }
        return totalPage > 0 ? totalPage : 1;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrent_cid() {
        return current_cid;
    }

    public void setCurrent_cid(int current_cid) {
        this.current_cid = current_cid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMinLoad() {
        return minLoad;
    }

    public void setMinLoad(String minLoad) {
        this.minLoad = minLoad;
    }

    public String getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(String maxLoad) {
        this.maxLoad = maxLoad;
    }

}
