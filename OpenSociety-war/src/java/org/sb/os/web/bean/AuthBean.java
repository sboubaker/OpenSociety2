/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sb.os.web.bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author boubaker
 */
@Named(value = "authBean")
@RequestScoped
public class AuthBean {

    /** Creates a new instance of AuthBean */
    public AuthBean() {
    }
    public String logout() {
    String result="/faces/login.xhtml?faces-redirect=true";
     
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
     
    try {
      request.logout();
    } catch (ServletException e) {
      result = "/?faces-redirect=true";
    }
     
    return result;
  }
}
