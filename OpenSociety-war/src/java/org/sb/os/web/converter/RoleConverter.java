/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sb.os.web.converter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.sb.os.ejb.bean.UserBeanLocal;
import org.sb.os.ejb.entity.Role;

/**
 *
 * @author boubaker
 */
@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter {

    UserBeanLocal userBean = lookupUserBeanLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            System.out.print(userBean.getRoleById(Integer.parseInt(value)).getName() + " --");
            return userBean.getRoleById(Integer.parseInt(value));
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value instanceof Role) {
            return ((Role) value).getId()+"";
        } else {
            return value + "";
        }
    }

    private UserBeanLocal lookupUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (UserBeanLocal) c.lookup("java:global/OpenSociety/OpenSociety-ejb/UserBean!org.sb.os.ejb.bean.UserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
