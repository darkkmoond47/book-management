package vn.iotstar.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;

import vn.iotstar.entity.Book;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderDetail;
import vn.iotstar.entity.User;
import vn.iotstar.model.CartItem;
import vn.iotstar.util.JPAUtil;

public class OrderDAO {

    // =========================================================
    // YC2 - TẠO ĐƠN HÀNG COD
    // =========================================================
    public Integer createOrder(
            User sessionUser,
            List<CartItem> cart) {

        EntityManager em =
                JPAUtil.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {

            transaction.begin();

            // Lấy User đang quản lý bởi EntityManager hiện tại
            User user =
                    em.find(
                            User.class,
                            sessionUser.getId()
                    );

            if (user == null) {
                throw new RuntimeException(
                        "Không tìm thấy tài khoản."
                );
            }

            Order order = new Order();

            order.setUser(user);

            order.setOrderDate(new Date());

            // Trạng thái ban đầu của đơn COD
            order.setStatus("Đơn hàng mới");

            // Phương thức thanh toán
            order.setPaymentMethod("COD");

            BigDecimal total =
                    BigDecimal.ZERO;

            // Duyệt toàn bộ giỏ hàng
            for (CartItem item : cart) {

                Integer bookId =
                        item.getBook().getBookid();

                // Lấy sách mới nhất từ database
                Book book =
                        em.find(
                                Book.class,
                                bookId,
                                LockModeType.PESSIMISTIC_WRITE
                        );

                if (book == null) {

                    throw new RuntimeException(
                            "Sách không tồn tại: "
                            + bookId
                    );
                }

                int quantity =
                        item.getQuantity();

                int stock =
                        book.getQuantity() == null
                                ? 0
                                : book.getQuantity();

                // Kiểm tra số lượng
                if (quantity <= 0) {

                    throw new RuntimeException(
                            "Số lượng không hợp lệ."
                    );
                }

                // Kiểm tra tồn kho
                if (quantity > stock) {

                    throw new RuntimeException(
                            "Sách \""
                            + book.getTitle()
                            + "\" chỉ còn "
                            + stock
                            + " cuốn."
                    );
                }

                // Giá hiện tại trong database
                BigDecimal price =
                        book.getPrice();

                if (price == null) {

                    price =
                            BigDecimal.ZERO;
                }

                BigDecimal subtotal =
                        price.multiply(
                                BigDecimal.valueOf(
                                        quantity
                                )
                        );

                OrderDetail detail =
                        new OrderDetail();

                detail.setBook(book);

                detail.setQuantity(quantity);

                detail.setPrice(price);

                detail.setSubtotal(subtotal);

                order.addDetail(detail);

                total =
                        total.add(subtotal);

                // Trừ tồn kho
                book.setQuantity(
                        stock - quantity
                );
            }

            order.setTotalAmount(total);

            // Lưu Order + OrderDetail
            em.persist(order);

            transaction.commit();

            return order.getId();

        } catch (Exception e) {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            throw e;

        } finally {

            if (em != null &&
                    em.isOpen()) {

                em.close();
            }
        }
    }


    // =========================================================
    // YC3 - LẤY LỊCH SỬ ĐƠN HÀNG
    // =========================================================
    public List<Order> findByUserAndStatus(
            Integer userId,
            String status) {

        EntityManager em =
                JPAUtil.getEntityManager();

        try {

            String jpql =
                    "SELECT DISTINCT o " +
                    "FROM Order o " +
                    "LEFT JOIN FETCH o.details d " +
                    "LEFT JOIN FETCH d.book " +
                    "WHERE o.user.id = :userId ";

            // Nếu không chọn "Tất cả"
            // thì lọc theo trạng thái
            if (status != null &&
                    !status.isEmpty() &&
                    !status.equals("ALL")) {

                jpql +=
                        "AND o.status = :status ";
            }

            jpql +=
                    "ORDER BY o.orderDate DESC";

            var query =
                    em.createQuery(
                            jpql,
                            Order.class
                    );

            query.setParameter(
                    "userId",
                    userId
            );

            if (status != null &&
                    !status.isEmpty() &&
                    !status.equals("ALL")) {

                query.setParameter(
                        "status",
                        status
                );
            }

            return query.getResultList();

        } finally {

            if (em != null &&
                    em.isOpen()) {

                em.close();
            }
        }
    }
}