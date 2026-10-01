package vn.iotstar.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid", nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 18, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(precision = 18, scale = 2, nullable = false)
    private BigDecimal subtotal;
}