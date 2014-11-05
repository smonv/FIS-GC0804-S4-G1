/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.client.category;

import entities.Categories;
import java.util.List;
import java.util.Locale.Category;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import models.CategoryModel;
import models.ProductModel;

/**
 *
 * @author LucreCk
 */
@ManagedBean
@ViewScoped
public class CategoryBean {

    @EJB
    private CategoryModel categoryModel;
    Categories currentCategory;

    public CategoryBean() {
        setCurrentCategory(null);
    }

    public List<Categories> getAllCategory() {
        return categoryModel.getAllCategory();
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public Categories getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Categories currentCategory) {
        this.currentCategory = currentCategory;
    }

   

   


}
