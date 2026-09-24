package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, unique = true)
    private String email;


    private String fullname;


    private String phone;


    private String passwd;


    private boolean admin;


    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

}