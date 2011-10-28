/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sb.os.web.bean;

import com.sun.faces.context.SessionMap;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boubaker
 */
@Named(value = "secBean")
@RequestScoped
public class SecBean {

    /** roleKey **/
    private static String ROLE = "role";

    /** Creates a new instance of SecBean */
    public SecBean() {
    }

    private ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public boolean hasRole(String role) {
        return getExternalContext().isUserInRole(role);
    }

    public boolean isAuthenticated() {
        return getRequest().getSession(false) != null;
    }

    public String getUserName() {
        return getRequest().getUserPrincipal().getName();
    }

    /**Logout**/
    public String logout() {
        String result = "/faces/login.xhtml?faces-redirect=true";
        try {
            getRequest().logout();
        } catch (ServletException e) {
            result = "/?faces-redirect=true";
        }

        return result;
    }
}
