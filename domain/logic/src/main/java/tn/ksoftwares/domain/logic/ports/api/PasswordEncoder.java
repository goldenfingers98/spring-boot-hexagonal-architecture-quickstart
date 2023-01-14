package tn.ksoftwares.domain.logic.ports.api;

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
}
