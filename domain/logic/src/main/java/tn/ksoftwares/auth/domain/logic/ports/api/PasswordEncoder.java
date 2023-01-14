package tn.ksoftwares.auth.domain.logic.ports.api;

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
}
