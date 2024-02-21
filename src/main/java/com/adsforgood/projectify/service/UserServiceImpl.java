package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validateUserDTOfields(UserDto userDto) throws Exception {
        if(userDto == null){
            throw new Exception("User must not be null");
        }
        if(userDto.getEmail().trim().equals("")){
            throw new Exception("Email must not be null or empty");
        }
        if(userDto.getPassword().trim().equals("")){
            throw new Exception("Password must not be null or empty");
        }
    }

    @Override
    public User convertUserDTOfieldsToUser(UserDto userDto) throws Exception {
        validateUserDTOfields(userDto);
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        return user;
    }

    public User findAnUserByEmailAndPassword(UserDto userDto) throws Exception{
        validateUserDTOfields(userDto);
        Optional<User> user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (user.isEmpty()){
            throw new Exception("Email or password must be wrong, try again");
        }else{
            return user.get();
        }
    }

    @Override
    public User findUserById(Long id) throws Exception {
        if(id == null){
            throw new Exception("Id must not be null");
        }
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("An user with that Id can't be found");
        }else{
            return user.get();
        }
    }

    @Override
    public User saveUser(UserDto userDto) throws Exception {
        validateUserDTOfields(userDto);
        User user = convertUserDTOfieldsToUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public User modifyUser(UserDto userDto) throws Exception {
        validateUserDTOfields(userDto);
        if(userDto.getId() == null){
            throw new Exception("Id must not be null");
        }else{
            User user = findUserById(userDto.getId());
            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        if (id == null){
            throw new Exception("Id must not be null");
        }else{
            User user = findUserById(id);
            userRepository.delete(user);
        }
    }


}
