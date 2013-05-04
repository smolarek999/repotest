package edu.agh.repotest.converter;

import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.dao.TestDeviceGroupDevices;
import edu.agh.repotest.session.TestDeviceGrouphasDeviceFacade;
import edu.agh.repotest.jsf.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class TestDeviceGrouphasDeviceConverter implements Converter {

    @EJB
    private TestDeviceGrouphasDeviceFacade ejbFacade;
    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    edu.agh.repotest.dao.TestDeviceGroupDevicesPK getKey(String value) {
        edu.agh.repotest.dao.TestDeviceGroupDevicesPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new edu.agh.repotest.dao.TestDeviceGroupDevicesPK();
        key.setDeviceId(Integer.parseInt(values[0]));
        key.setDeviceGroupsId(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(Device device ,  TestDeviceGroup deviceGroup) {
        StringBuffer sb = new StringBuffer();
        sb.append(device.getIdDevice());
        sb.append(SEPARATOR);
        sb.append(deviceGroup.getId());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof TestDeviceGroupDevices) {
            TestDeviceGroupDevices o = (TestDeviceGroupDevices) object;
            return getStringKey(o.getDevice(), o.getTestDeviceGroup());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TestDeviceGroupDevices.class.getName()});
            return null;
        }
    }
}
