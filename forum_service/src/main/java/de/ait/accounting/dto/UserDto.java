package de.ait.accounting.dto;

import com.mongodb.internal.authentication.SaslPrep;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserDto {
    private String login;
            private String firstName;
            private String lastName;
            @Singular
            private Set<String> roles;
}
