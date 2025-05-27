package de.ait.accounting.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoleDto {
    private String login;
    @Singular
    private Set<String> roles;
}
