/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import beans.CategoriesFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import control.util.JsfUtil;
import control.util.PagingInfo;
import entities.Categories;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Lucre Ck
 */
public class CategoriesController {

    public CategoriesController() {
        pagingInfo = new PagingInfo();
        converter = new CategoriesConverter();
    }
    private Categories categories = null;
    private List<Categories> categoriesItems = null;
    private CategoriesFacade jpaController = null;
    private CategoriesConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "FIS-GC0804-S4-G1PU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public CategoriesFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (CategoriesFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "categoriesJpa");
        }
        return jpaController;
    }

    public SelectItem[] getCategoriesItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getCategoriesItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Categories getCategories() {
        if (categories == null) {
            categories = (Categories) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCategories", converter, null);
        }
        if (categories == null) {
            categories = new Categories();
        }
        return categories;
    }

    public String listSetup() {
        reset(true);
        return "categories_list";
    }

    public String createSetup() {
        reset(false);
        categories = new Categories();
        return "categories_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(categories);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Categories was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("categories_detail");
    }

    public String editSetup() {
        return scalarSetup("categories_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        categories = (Categories) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCategories", converter, null);
        if (categories == null) {
            String requestCategoriesString = JsfUtil.getRequestParameter("jsfcrud.currentCategories");
            JsfUtil.addErrorMessage("The categories with id " + requestCategoriesString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String categoriesString = converter.getAsString(FacesContext.getCurrentInstance(), null, categories);
        String currentCategoriesString = JsfUtil.getRequestParameter("jsfcrud.currentCategories");
        if (categoriesString == null || categoriesString.length() == 0 || !categoriesString.equals(currentCategoriesString)) {
            String outcome = editSetup();
            if ("categories_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit categories. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(categories);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Categories was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCategories");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Categories was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Categories> getCategoriesItems() {
        if (categoriesItems == null) {
            getPagingInfo();
            categoriesItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return categoriesItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "categories_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "categories_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        categories = null;
        categoriesItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Categories newCategories = new Categories();
        String newCategoriesString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCategories);
        String categoriesString = converter.getAsString(FacesContext.getCurrentInstance(), null, categories);
        if (!newCategoriesString.equals(categoriesString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
