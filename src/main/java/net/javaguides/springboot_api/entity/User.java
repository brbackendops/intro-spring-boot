package net.javaguides.springboot_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="username",nullable = false)
    private String username;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;


    @Column(name="created_at",nullable = false, updatable = false) // columnDefinition = "TIMESTAMP DEFAULT NOW()"
//    @CreationTimestamp
    private LocalDateTime createdAt;

    // runs before inserting the row
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

}
