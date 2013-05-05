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
        FacesContext context = FacesContext.getCurrentInstance();
        TestDeviceGroupController bean = (TestDeviceGroupController) context.getApplication().evaluateExpressionGet(context, "#{testDeviceGroupController}", TestDeviceGroupController.class);
        
        return bean.getDevicesGroups().get(Integer.valueOf(value));
    }

    edu.agh.repotest.dao.TestDeviceGroupPK getKey(String value) {
        edu.agh.repotest.dao.TestDeviceGroupPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new edu.agh.repotest.dao.TestDeviceGroupPK();
        key.setIdTestDeviceGroup(Integer.parseInt(values[0]));
        key.setTestidTest(Integer.parseInt(values[1]));
        return key;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        FacesContext context = FacesContext.getCurrentInstance();
        TestDeviceGroupController bean = (TestDeviceGroupController) context.getApplication().evaluateExpressionGet(context, "#{testDeviceGroupController}", TestDeviceGroupController.class);
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TestDeviceGroup) {
            
            TestDeviceGroup o = (TestDeviceGroup) object;
             bean.getDevicesGroups().indexOf(o);
            return String.valueOf(bean.getDevicesGroups().indexOf(o));
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TestDeviceGroup.class.getName()});
            return null;
        }
    }
}
