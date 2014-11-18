package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@FacesValidator("productExists")
public class ProductExistsValidator implements Validator {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        long pid = (long) value;
        long result = (long) em.createNamedQuery("Products.exists").setParameter("pid", pid).getSingleResult();
        if (result == 0) {
            FacesMessage msg = new FacesMessage("Product not exists!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
