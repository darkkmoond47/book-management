package vn.iotstar.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userid",
            nullable = false
    )
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date orderDate;

    @Column(
            precision = 18,
            scale = 2,
            nullable = false
    )
    private BigDecimal totalAmount;

    @Column(name = "status", nullable = false, columnDefinition = "NVARCHAR(50)")
    private String status;

    @Column(nullable = false)
    private String paymentMethod;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderDetail> details =
            new ArrayList<>();

    public void addDetail(OrderDetail detail) {

        details.add(detail);

        detail.setOrder(this);
    }
}