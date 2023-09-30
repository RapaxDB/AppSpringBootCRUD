package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(int id) {
        Query query = entityManager.createQuery(
                        "from User as user where user.id =:id", User.class);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery(
                "select user from User user", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
