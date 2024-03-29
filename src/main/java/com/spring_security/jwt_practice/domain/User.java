package com.spring_security.jwt_practice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.spring_security.jwt_practice.domain.enums.UserRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (length = 25, nullable = false)
    private String firstName;
    @Column (length = 25, nullable = false)
    private String LastName;

    @Column (length = 25, nullable = false, unique = true)
    private String username;
    @Column(length = 255, nullable = false) // length 255 olma sebebi; password
    private String password;
    @JoinTable( name="tbl_user_role",
            joinColumns = @JoinColumn(name= "user_id"),
            inverseJoinColumns = @JoinColumn (name="role_id"))
    @ManyToMany (fetch=FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
