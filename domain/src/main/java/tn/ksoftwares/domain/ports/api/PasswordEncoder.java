package tn.ksoftwares.domain.ports.api;

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
}
