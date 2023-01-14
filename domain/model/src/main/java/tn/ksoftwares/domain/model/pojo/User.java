package tn.ksoftwares.domain.model.pojo;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.ksoftwares.domain.model.utils.BasePojo;
import tn.ksoftwares.domain.model.utils.Email;
import tn.ksoftwares.domain.model.utils.Name;
import tn.ksoftwares.domain.model.utils.Password;
import tn.ksoftwares.domain.model.utils.Username;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BasePojo {

    private UUID id;

    @NotNull(message = "Firstname must not be null.")
    private Name firstname;

    private Name middlename;

    @NotNull(message = "Lastname must not be null.")
    private Name lastname;

    @NotNull(message = "Email must not be null.")
    private Email email;

    @NotNull(message = "Username must not be null.")
    private Username username;
    
    @NotNull(message = "Password must not be null.")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Password password;

}
