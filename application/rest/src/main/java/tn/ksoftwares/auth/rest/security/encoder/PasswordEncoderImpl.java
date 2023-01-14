package tn.ksoftwares.auth.rest.security.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.ksoftwares.auth.domain.logic.ports.api.PasswordEncoder;

public class PasswordEncoderImpl extends BCryptPasswordEncoder implements PasswordEncoder {

}
