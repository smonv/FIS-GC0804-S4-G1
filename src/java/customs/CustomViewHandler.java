/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customs;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author SolomonT
 */
public class CustomViewHandler extends ViewHandlerWrapper {

    private ViewHandler wrapper;

    public CustomViewHandler(ViewHandler wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String getActionURL(FacesContext context, String viewId) {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        // remaining on the same view keeps URL state 
        String requestViewID = request.getRequestURI().substring(
                request.getContextPath().length());
        if (requestViewID.equals(viewId)) {

            // keep RESTful URLs and query strings
            String action = (String) request.getAttribute(
                    RequestDispatcher.FORWARD_REQUEST_URI);
            if (action == null) {
                action = request.getRequestURI();
            }
            if (request.getQueryString() != null) {
                return action + "?" + request.getQueryString();
            } else {
                return action;
            }
        } else {

            // moving to a new view drops old URL state 
            return super.getActionURL(context, viewId);
        }
    }

    @Override
    public ViewHandler getWrapped() {
        return wrapper;
    }

}
