package de.ait.accounting.service;

import de.ait.accounting.dao.UserAccountRepository;
import de.ait.accounting.dto.RoleDto;
import de.ait.accounting.dto.UserDto;
import de.ait.accounting.dto.UserRegisterDto;
import de.ait.accounting.dto.UserUpdateDto;
import de.ait.accounting.dto.exception.UserExistsException;
import de.ait.accounting.dto.exception.UserNotFoundException;
import de.ait.accounting.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserAccountServiceIml implements UserAccountService{
    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        if(userAccountRepository.existsById(userRegisterDto.getLogin())) {
            throw new UserExistsException();
        }
        UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
        String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
        userAccount.setPassword(password);
        UserAccount newUserAccount = userAccountRepository.save(userAccount);

        return modelMapper.map(newUserAccount, UserDto.class);
    }

    @Override
    public UserDto getUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto removeUser(String login) {
        return null;
    }

    @Override
    public UserDto updateUser(String login, UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public RoleDto changeRolesList(String login, String role, boolean isAddRole) {
        return null;
    }

    @Override
    public void changePassword(String login, String newPassword) {

    }
}
