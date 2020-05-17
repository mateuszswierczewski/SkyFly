package pl.mswierczewski.skyfly.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mswierczewski.skyfly.models.Person;
import pl.mswierczewski.skyfly.validation.constraints.Password;
import pl.mswierczewski.skyfly.validation.constraints.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class SkyFlyUser implements UserDetails, Serializable {

    @Id
    @NotNull(message = "{pl.mswierczewski.skyfly.security.user.SkyFlyUser.username.NotNull.message}")
    @Size(min = 4, message ="{pl.mswierczewski.skyfly.security.user.SkyFlyUser.username.Size.message}")
    @Unique(service = SkyFlyUserService.class, columnName = "username",
            message = "{pl.mswierczewski.skyfly.security.user.SkyFlyUser.username.Unique.message}")
    private String username;

    @Size(min = 6, max = 24, message = "{pl.mswierczewski.skyfly.security.user.SkyFlyUser.password.Size.message}")
    @Password
    private String password;

    @ElementCollection(targetClass = SkyFlyUserRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<SkyFlyUserRole> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details", nullable = false)
    private Person userDetails;

    @Transient
    private boolean isAccountNonExpired;
    @Transient
    private boolean isAccountNonLocked;
    @Transient
    private boolean isCredentialsNonExpired;
    @Transient
    private boolean isEnabled;

    public SkyFlyUser(){
        initialize();
    }

    public SkyFlyUser(String username, String password, Set<SkyFlyUserRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        initialize();
    }

    private void initialize(){
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Person getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Person userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void addRole(SkyFlyUserRole role){
        roles.add(role);
    }

    @Override
    public String toString() {
        return "SkyFlyUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
