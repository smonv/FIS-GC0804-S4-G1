package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("imageValidator")
public class ImageValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;
        FacesMessage msg = null;
        if (file != null) {
            if (!"image/jpeg".equals(file.getContentType()) && !"image/png".equals(file.getContentType())) {
                msg = new FacesMessage("Wrong file type! Only jpg, png, jpeg!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

            if (file.getSize() > 3145728) {
                msg = new FacesMessage("File to large! Max size is 3mb!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }

}
