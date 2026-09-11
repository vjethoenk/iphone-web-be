package com.example.iphone_web_be.modules.user.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import com.example.iphone_web_be.modules.role.entity.Role;
import com.example.iphone_web_be.modules.user.enums.Gender;
import com.example.iphone_web_be.modules.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(name = "customer_code",  unique = true, length = 20)
    String customerCode;

    @Column(name = "username", nullable = false, length = 100)
    String username;

    @Column(unique = true, length = 100)
    String email;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    Set<Role> roles = new HashSet<>();

    @Column( unique = true, length = 10)
    String phone;

    @Column(name = "citizen_id", unique = true, length = 20)
    String citizenId;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    Gender gender;

    @Column(length = 255)
    String address;

    @Column(length = 255)
    String avatar;

    @Column(nullable = false, length = 255)
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    UserStatus status;

}
