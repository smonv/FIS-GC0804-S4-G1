package beans.client.product;

import entities.Categories;
import entities.Manufacturers;
import entities.Nations;
import entities.Products;
import helpers.ApplicationHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.CategoryModel;
import models.ManufacturerModel;
import models.NationModel;
import models.ProductModel;

@ManagedBean
@RequestScoped
public class IndexProduct {

    @EJB
    private ManufacturerModel manufacturerModel;

    @EJB
    private NationModel nationModel;

    @EJB
    private CategoryModel categoryModel;

    @EJB
    private ProductModel productModel;

    //bean variables
    private List<Products> products;
    private int current_page = 1;
    private Nations nation;
    private int pageSize = 6;
    private long totalProduct;
    private long totalPage;
    private int currentCid = 0;
    private int currentMinLoad = 0;
    private int currentMaxLoad = 2000;
    private int currentMid = 0;
    private int currentNid = 0;
    //view param
    private String page;
    private String cid;
    private String minLoad;
    private String maxLoad;
    private String mid;
    private String mode;
    private String nid;

    public IndexProduct() {
    }

    public void init() {

        currentCid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0; //check cid
        Categories category = categoryModel.getById(currentCid); //get category by cid;

        currentMinLoad = ApplicationHelper.isInteger(minLoad) ? Integer.parseInt(minLoad) : 0; //check min load
        currentMaxLoad = ApplicationHelper.isInteger(maxLoad) ? Integer.parseInt(maxLoad) : 2000; //check max load
        currentMid = ApplicationHelper.isInteger(mid) ? Integer.parseInt(mid) : 0;// check nid
        Manufacturers manufacturer = manufacturerModel.getById(currentMid);
        currentNid = ApplicationHelper.isInteger(nid) ? Integer.parseInt(nid) : 0; //check valid nation id
        nation = nationModel.getById(currentNid); //get nation to filter

        ///////////
        current_page = ApplicationHelper.getCurrentPage(page);
        products = productModel.getAll(current_page - 1, pageSize, category, currentMinLoad, currentMaxLoad, manufacturer, nation);
        totalProduct = productModel.countAll(category, currentMinLoad, currentMaxLoad, manufacturer, nation);

    }

    public List<Categories> getAllCategories() {
        return categoryModel.getAllCategory();
    }

    public List<Nations> getAllNations() {
        return nationModel.getAll();
    }

    public List<Manufacturers> getAllManufacturers() {
        return nation != null ? manufacturerModel.getAll(nation) : manufacturerModel.getAll();
    }

    //SET GET
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
        return currentCid;
    }

    public void setCurrent_cid(int current_cid) {
        this.currentCid = current_cid;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

}
