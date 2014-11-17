package beans.admin.contract;

import entities.Admins;
import entities.Contracts;
import entities.OrderProductDetails;
import entities.Orders;
import entities.Products;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import models.ContractModel;
import models.OrderModel;
import models.ProductModel;

@ManagedBean
@ViewScoped
public class AdminNewContract {

    @EJB
    private ContractModel contractModel;

    @EJB
    private ProductModel productModel;

    @EJB
    private OrderModel orderModel;

    ///bean variables
    private Orders order;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String clientRequirements;
    private String paymentDetails;
    //view params
    private String number;

    public AdminNewContract() {
    }

    public void init() {
        order = orderModel.getByNumber(number);
        if (order == null) {
            ApplicationHelper.redirect("/admin/404.xhtml", true);
        }
        if (order.getContracts() != null) {
            ApplicationHelper.addMessage("Order has contract, can't create more!");
            ApplicationHelper.redirect("/admin/order/view.xhtml?number=" + order.getNumber(), true);
        }
    }

    public void create() {
        order = orderModel.getByNumber(number);
        Contracts contract = new Contracts();
        contract.setAdminId(new Admins(1)); //will fix after have admin login and filter
        contract.setOrderId(order);
        contract.setClientName(clientName);
        contract.setClientEmail(clientEmail);
        contract.setClientPhone(clientPhone);
        contract.setClientRequirements(clientRequirements);
        contract.setPaymentDetails(paymentDetails);
        contract.setTotalProductPrice(this.countTotalProductPrice(order));
        contract.setTotalConstructionPrice(this.countTotalContructionPrice(order));
        contract.setTotalConstructionTime(this.countTotalCOntructionTime(order));
        contract.setCreateAt(PersistenceHelper.getCurrentTime());

        boolean result = contractModel.create(contract);
        if (result) {
            ApplicationHelper.addMessage("Contract created!");
            ApplicationHelper.redirect("/admin/order/view.xhtml?number=" + number, result);
        } else {
            ApplicationHelper.addMessage("Failed to create new contract!");
            ApplicationHelper.redirect("/admin/contract/new.xhtml?number=" + number, result);
        }
    }

    public BigDecimal countTotalProductPrice(Orders order) {
        BigDecimal totalProductPrice = BigDecimal.ZERO;
        List<OrderProductDetails> opds = order.getOrderProductDetailsList();
        for (OrderProductDetails opd : opds) {
            Products p = opd.getProductId();
            totalProductPrice = totalProductPrice.add(p.getPrice().multiply(new BigDecimal(opd.getQuantity())));
        }
        return totalProductPrice;
    }

    public BigDecimal countTotalContructionPrice(Orders order) {
        BigDecimal totalContructionPrice = BigDecimal.ZERO;
        List<OrderProductDetails> opds = order.getOrderProductDetailsList();
        for (OrderProductDetails opd : opds) {
            Products p = opd.getProductId();
            BigDecimal realHeight = opd.getHeightOfFloor().multiply(new BigDecimal(opd.getFloors()));
            totalContructionPrice = totalContructionPrice.add(p.getConstructionPrice().multiply(realHeight));
        }
        return totalContructionPrice.setScale(0, RoundingMode.HALF_UP);
    }

    public int countTotalCOntructionTime(Orders order) {
        BigDecimal totalContructionTime = BigDecimal.ZERO;
        List<OrderProductDetails> opds = order.getOrderProductDetailsList();
        for (OrderProductDetails opd : opds) {
            Products p = opd.getProductId();
            BigDecimal realHeight = opd.getHeightOfFloor().multiply(new BigDecimal(opd.getFloors()));
            totalContructionTime = totalContructionTime.add(realHeight.multiply(new BigDecimal(p.getConstructionTime())));
        }
        return totalContructionTime.setScale(0, RoundingMode.HALF_UP).intValueExact();
    }

    //SET GET
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientRequirements() {
        return clientRequirements;
    }

    public void setClientRequirements(String clientRequirements) {
        this.clientRequirements = clientRequirements;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

}
