package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.iotstar.entity.Book;
import vn.iotstar.util.JPAUtil;

import java.util.List;

public class BookDAO {

    // Lấy sách cho trang Home - 6 sách/trang
    public List<Book> findAll(int page) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT b FROM Book b",
                    Book.class
            )
            .setFirstResult((page - 1) * 6)
            .setMaxResults(6)
            .getResultList();

        } finally {
            em.close();
        }
    }

    // Lấy sách cho Admin - 6 sách/trang
    public List<Book> findAllAdmin(int page) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT b FROM Book b",
                    Book.class
            )
            .setFirstResult((page - 1) * 6)
            .setMaxResults(6)
            .getResultList();

        } finally {
            em.close();
        }
    }

    // Đếm tổng số sách
    public long count() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(b) FROM Book b",
                    Long.class
            ).getSingleResult();

        } finally {
            em.close();
        }
    }

    public Book findById(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Book.class, id);

        } finally {
            em.close();
        }
    }

    public void save(Book book) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {

            tr.begin();

            if (book.getBookid() == null) {
                em.persist(book);
            } else {
                em.merge(book);
            }

            tr.commit();

        } catch (Exception e) {

            if (tr.isActive()) {
                tr.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }

    public void delete(int id) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {

            tr.begin();

            Book book = em.find(Book.class, id);

            if (book != null) {
                em.remove(book);
            }

            tr.commit();

        } catch (Exception e) {

            if (tr.isActive()) {
                tr.rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
        }
    }
}