package com.vshvet.firstrelease.Entity;


import com.vshvet.firstrelease.ConstValue;
import com.vshvet.firstrelease.Payload.Request.RegistrationRequest;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        },
        schema = ConstValue.SCHEMA_NAME)
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_user_seq")
    @SequenceGenerator(name = "id_user_seq", initialValue = 50)
    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles;

    private boolean enabled;



    public User(Long id) {
        this.id = id;
    }

    public User(RegistrationRequest registrationRequest) {
        this.username = registrationRequest.getUsername();
        this.password = registrationRequest.getPassword();
        this.email = registrationRequest.getEmail();
    }

    public User() {
        this.enabled=false;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled=false;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "id_user_id_seq")
    @SequenceGenerator(name = "id_user_id_seq", initialValue = 2)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @NotBlank
    @Size(max = 64)
    @Column(name = "username", nullable = false, length = 64)
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @NotBlank
    @Size(max = 120)
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
