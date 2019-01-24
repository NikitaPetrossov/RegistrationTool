package petrossov.service.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import petrossov.service.models.State;
import petrossov.service.models.User;

import java.util.Collection;
import java.util.Collections;
//класс адаптер  - адаптирует нашу модель к модели поведения Spring Security
public class UserDetailsImpl implements UserDetails {
    private User user;

    public User getUser() {
        return user;
    }

    public UserDetailsImpl(User user) {
        this.user = user;
    }
//сообщаем springу какие права есть у пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       String userRole = user.getRole().name();
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole);

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getState().equals(State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getState().equals(State.ACTIVE);
    }
}
