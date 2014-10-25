/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import helpers.PersistenceHelper;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cu Beo
 */
@FacesValidator("uniqueUsername")
public class UniqueUsernameValidator implements Validator {

    @PersistenceContext
    EntityManager em;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = value.toString();

        long result = (long) em.createNamedQuery("Clients.uniqueUsername").setParameter("username", username).getSingleResult();
        if (result == 1) {
            FacesMessage msg = new FacesMessage("Username exists! Please enter another!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
