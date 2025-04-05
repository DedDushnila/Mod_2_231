package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(Long id, String name, String lastName, Integer age) {
        User user = em.find(User.class, id);

        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }

        if (lastName != null && !lastName.isEmpty()) {
            user.setLastName(lastName);
        }

        if (age >= 0 && age != null) {
            user.setAge(age);
        }

        em.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

}