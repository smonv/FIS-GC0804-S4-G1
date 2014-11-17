package beans.admin.statistic;

import entities.Contracts;
import helpers.ApplicationHelper;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.ContractModel;

@ManagedBean
@RequestScoped
public class ContractStatistic {
    @EJB
    private ContractModel contractModel;

    //bean variables
    private List<Contracts> contracts;
    //view params
    private String page;
    
    public ContractStatistic() {
    }
    
    public void init(){
        contracts = contractModel.getAll();
    }

    
    
    
    public String formatDate(Date date){
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }
    
    ///////SET GET
    public List<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contracts> contracts) {
        this.contracts = contracts;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
    
    
    
}
