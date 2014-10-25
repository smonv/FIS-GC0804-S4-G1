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

/**
 *
 * @author Cu Beo
 */
@FacesValidator("uniqueEmail")
public class UniqueEmailValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = value.toString();
        EntityManager em = PersistenceHelper.getEntityManager();
        long result = (long) em.createNamedQuery("Clients.uniqueEmail").setParameter("email", email).getSingleResult();
        if (result == 1) {
            FacesMessage msg = new FacesMessage("Email exists! Please choose another!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}