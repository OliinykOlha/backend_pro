package de.ait.accounting.service;

import de.ait.accounting.dto.RoleDto;
import de.ait.accounting.dto.UserDto;
import de.ait.accounting.dto.UserRegisterDto;
import de.ait.accounting.dto.UserUpdateDto;

public interface UserAccountService {
    UserDto registerUser(UserRegisterDto userRegisterDto);
    UserDto getUser(String login);
    UserDto removeUser(String login);
    UserDto updateUser(String login, UserUpdateDto userUpdateDto);
    RoleDto changeRolesList(String login, String role, boolean isAddRole); // false - если удалён
    void changePassword(String login, String newPassword);


}
