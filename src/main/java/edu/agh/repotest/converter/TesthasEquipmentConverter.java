package edu.agh.repotest.converter;

import edu.agh.repotest.dao.TesthasEquipment;
import edu.agh.repotest.session.TesthasEquipmentFacade;
import edu.agh.repotest.jsf.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TesthasEquipmentConverter implements Converter {

    @EJB
    private TesthasEquipmentFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    edu.agh.repotest.dao.TesthasEquipmentPK getKey(String value) {
        edu.agh.repotest.dao.TesthasEquipmentPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new edu.agh.repotest.dao.TesthasEquipmentPK();
        key.setTestidTest(Integer.parseInt(values[0]));
        key.setEquipmentidEquipment(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(edu.agh.repotest.dao.TesthasEquipmentPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getTestidTest());
        sb.append(SEPARATOR);
        sb.append(value.getEquipmentidEquipment());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TesthasEquipment) {
            TesthasEquipment o = (TesthasEquipment) object;
            return o.getId().toString();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TesthasEquipment.class.getName()});
            return null;
        }
    }
}
