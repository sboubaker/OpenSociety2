/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sb.os.ejb.bean;

import java.util.List;
import javax.ejb.Local;
import org.sb.os.ejb.entity.Role;
import org.sb.os.ejb.entity.User;

/**
 *
 * @author boubaker
 */
@Local
public interface UserBeanLocal {

    List<Role> getRoles();

    List<User> getUsers();

    boolean saveOrUpdateUser(User user);

    User getUserById(int id);

    Role getRoleById(int id);
    
}
