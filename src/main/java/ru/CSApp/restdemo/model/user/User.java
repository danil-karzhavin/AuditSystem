package ru.CSApp.restdemo.model.user;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String lastname;
    @Column(name = "name", nullable = false)
    String username;
    String surname;
    @Column(name = "password", nullable = false)
    String password;
    LocalDate ExpirationDate;

    // интерфейс Колллекции объектов, каждый из которых должен иметь реализацию интерфейса GrantedAuthority https://chataibot.ru/app/chat?chat_id=516961
    @Transient // Это поле не будет сохраняться в базе данных
    private Collection<? extends GrantedAuthority> authorities;
    @Transient // Это поле не будет сохраняться в базе данных
    private boolean accountNonExpired;
    @Transient // Это поле не будет сохраняться в базе данных
    private boolean accountNonLocked;
    @Transient // Это поле не будет сохраняться в базе данных
    private boolean credentialsNonExpired;
    @Transient // Это поле не будет сохраняться в базе данных
    private boolean enabled;

    public User(){};
    public User(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities,
                boolean accountNonExpired, boolean accountNonLocked,
                boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getLastname() {
        return lastname;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getExpirationDate() {
        return ExpirationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        ExpirationDate = expirationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    - Возвращает коллекцию полномочий (прав), которые присвоены пользователю.
//    - Каждый элемент представляет собой объект, реализующий интерфейс `GrantedAuthority`.
//    - Полномочия могут быть представлены, например, в виде ролей (например, `ROLE_USER`, `ROLE_ADMIN`).
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}