/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import entities.Clients;
import entities.OrderProductDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cu Beo
 */
public class SessionHelper {

    public static Map<String, Object> getSessionMap() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, Object> session = externalContext.getSessionMap();
        return session;
    }

    public static List<OrderProductDetails> getSessionOrderProductDetails() {
        Map<String, Object> session = getSessionMap();
        if (session.get("order_product_details") != null) {
            return (List<OrderProductDetails>) session.get("order_product_details");
        } else {
            List<OrderProductDetails> opds = new ArrayList<>();
            session.put("order_product_details", opds);
            return opds;
        }
    }

    public static Clients getCurrentClient() {
        Map<String, Object> session = getSessionMap();
        return session.get("client") != null ? (Clients) session.get("client") : null;
    }
}
