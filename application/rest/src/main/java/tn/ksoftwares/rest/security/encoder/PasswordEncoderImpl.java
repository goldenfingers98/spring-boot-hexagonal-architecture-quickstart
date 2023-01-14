package tn.ksoftwares.rest.security.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.ksoftwares.domain.logic.ports.api.PasswordEncoder;

public class PasswordEncoderImpl extends BCryptPasswordEncoder implements PasswordEncoder {

}
