/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sb.os.ejb.bean;

import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.sb.os.ejb.entity.Role;
import org.sb.os.ejb.entity.User;

/**
 *
 * @author boubaker
 */
@DeclareRoles({"ADMIN"})
@Stateless
public class UserBean implements UserBeanLocal {
    @PersistenceContext(unitName = "OpenSociety-ejbPU")
    private EntityManager em;
    
    @RolesAllowed("ADMIN")
    @Override
    public List<Role> getRoles() {
        Query query=em.createNamedQuery("Role.findAll");
        return query.getResultList();
    }
    @RolesAllowed("ADMIN")
    @Override
    public List<User> getUsers() {
       Query query=em.createNamedQuery("User.findAll");
       return query.getResultList();
    }
    @RolesAllowed("ADMIN")
    @Override
    public boolean saveOrUpdateUser(User user) {
        try{
            persist(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @RolesAllowed("ADMIN")
    @Override
    public User getUserById(int id) {
       Query query=em.createNamedQuery("User.findById").setParameter("id", id);
       return (User) query.getSingleResult();
    }
    @RolesAllowed("ADMIN")
    @Override
    public Role getRoleById(int id) {
        Query query=em.createNamedQuery("Role.findById").setParameter("id", id);
       return (Role) query.getSingleResult();
    }
    public void persist(Object object) {
        em.merge(object);
    }

    

    

    

    
    
}
