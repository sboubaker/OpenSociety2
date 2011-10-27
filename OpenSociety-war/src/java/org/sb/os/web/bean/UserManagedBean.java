/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sb.os.web.bean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.sb.os.ejb.bean.UserBeanLocal;
import org.sb.os.ejb.entity.Role;
import org.sb.os.ejb.entity.User;

/**
 *
 * @author boubaker
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private UserBeanLocal userBean;
    /** A single session user object **/
    private User user;

    /** Creates a new instance of UserManagedBean */
    public UserManagedBean() {
    }
    //Property actions

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
    public List<Role> getRoles() {
        return userBean.getRoles();
    }

    public List<User> getUsers() {
        return userBean.getUsers();
    }
    //webflow actions
    
    public String persistUser() {
        if (user.getDtcreate() == null) {
            user.setDtcreate(new Date());
            user.setIsactive(1);
        }
        user.setDtmodif(new Date());
        return userBean.saveOrUpdateUser(user) + "";
    }
}
