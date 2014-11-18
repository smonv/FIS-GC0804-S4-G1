package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordConfirmationMatch")
public class PasswordConfirmationValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password_confirmation = value.toString();
        UIInput uiInputPassword = (UIInput) context.getViewRoot().findComponent("registerForm:password");
        if (uiInputPassword.getValue() != null) {
            String password = uiInputPassword.getValue().toString();

            if (!password.equals(password_confirmation)) {
                uiInputPassword.setValid(false);
                FacesMessage msg = new FacesMessage("Password confirmation must match password!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }

}
