package service;

import dao.UsersDao;
import dao.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nathanael Villemin
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersDao usersDao;
    
    @Override
    public boolean add(String name, String email, String password) {
        if(this.usersDao.find(name) == null) {
            UsersEntity user = new UsersEntity(name, email, password);
            this.usersDao.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String name) {
        UsersEntity user = this.usersDao.find(name);
        if(user != null) {
            this.usersDao.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public UsersEntity find(String name) {
        return this.usersDao.find(name);
    }
}