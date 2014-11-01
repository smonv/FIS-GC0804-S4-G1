/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author SolomonT
 */
@FacesValidator("longType")
public class LongTypeValidation implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean valid = false;
        try {
            long long_value = Long.parseLong((String) value);
            valid = true;
        } catch (NumberFormatException e) {
            valid = false;
        }
        
        if(!valid){
            FacesMessage msg = new FacesMessage("Can't convert number");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
