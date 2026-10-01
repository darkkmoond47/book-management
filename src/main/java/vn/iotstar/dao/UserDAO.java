package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.iotstar.entity.User;
import vn.iotstar.util.JPAUtil;

public class UserDAO {

    // ĐĂNG NHẬP
    public User login(String email, String password) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT u FROM User u " +
                    "WHERE u.email = :email " +
                    "AND u.passwd = :password",
                    User.class
            )
            .setParameter("email", email)
            .setParameter("password", password)
            .getSingleResult();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


    // ĐĂNG KÝ / LƯU USER
    public void save(User user) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            em.persist(user);

            transaction.commit();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}