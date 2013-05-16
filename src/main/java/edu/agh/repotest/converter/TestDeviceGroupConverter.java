package edu.agh.repotest.converter;

import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.jsf.TestController;
import edu.agh.repotest.jsf.TestDeviceGroupController;
import edu.agh.repotest.session.TestDeviceGroupFacade;
import edu.agh.repotest.jsf.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TestDeviceGroupConverter implements Converter {

    @EJB
    private TestDeviceGroupFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        System.out.println("conv conv");
        return this.ejbFacade.find(getKey(value));
    }

    Integer getKey(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TestDeviceGroup) {
            
            TestDeviceGroup o = (TestDeviceGroup) object;
            return String.valueOf(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TestDeviceGroup.class.getName()});
            return null;
        }
    }
}
