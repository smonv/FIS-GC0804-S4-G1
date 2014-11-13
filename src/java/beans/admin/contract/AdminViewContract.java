package beans.admin.contract;

import entities.Contracts;
import helpers.ApplicationHelper;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ContractModel;

@ManagedBean
@RequestScoped
public class AdminViewContract {
    
    @EJB
    private ContractModel contractModel;

    //bean variable
    private Contracts contract;
    private int currentCid;
    //view params
    private String cid;
    
    public AdminViewContract() {
    }
    
    public void init() {
        currentCid = ApplicationHelper.isInteger(cid) ? Integer.parseInt(cid) : 0;
        contract = contractModel.getById(currentCid);
        if (contract == null) {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
        }
    }

    ///SET GET
    public Contracts getContract() {
        return contract;
    }
    
    public void setContract(Contracts contract) {
        this.contract = contract;
    }
    
    public String getCid() {
        return cid;
    }
    
    public void setCid(String cid) {
        this.cid = cid;
    }
    
}
