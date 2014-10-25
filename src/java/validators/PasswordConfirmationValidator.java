/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Cu Beo
 */
@FacesValidator("passwordConfirmationMatch")
public class PasswordConfirmationValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password_confirmation = value.toString();
        UIInput uiInputPassword = (UIInput) context.getViewRoot().findComponent("registerForm:password");
        String password = uiInputPassword.getValue().toString();

        if (!password.equals(password_confirmation)) {
            uiInputPassword.setValid(false);
            FacesMessage msg = new FacesMessage("Password must match password confirm!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
