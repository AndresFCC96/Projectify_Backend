package com.adsforgood.projectify.service.impl;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.mapper.UserMapper;
import com.adsforgood.projectify.repository.UserRepository;
import com.adsforgood.projectify.service.UserService;
import com.adsforgood.projectify.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAllUsers() throws Exception {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()){
            throw new ExceptionManager.EmptyListException("User");
        }else {
            return userList;
        }
    }

    @Override
    public User findByEmail(String email) throws Exception {
        if (!Utils.isAString(email) || !Utils.isAValidEmail(email)){
            throw new ExceptionManager.InvalidValueException("email");
        }else {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty()){
                throw new ExceptionManager.NoEntityFoundWithValue("User", email);
            }else{
                return user.get();
            }
        }
    }

    @Override
    public User findById(Long id) throws Exception {
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
    public User modifyUser(UserDto userDto) throws Exception {
        if (!Utils.isAnObject(userDto)){
            throw new ExceptionManager.NotAValidEntity("UserDto");
        }else{
            if(!Utils.isNumeric(userDto.getId().toString())){
                throw new ExceptionManager.InvalidValueException("Id");
            }else {
                validateName(userDto.getFirstName(), userDto.getLastName());
                validateEmail(userDto.getEmail());
                validatePassword(userDto.getPassword());
                validatePercentage(userDto.getWeeklyPercentage());
                User user = UserMapper.convertUserDtoToUser(userDto);
                user.setId(userDto.getId());
                return userRepository.save(user);
            }
        }
    }

    @Override
    public boolean validateName(String firstName, String lasstName) throws Exception {
        if (!Utils.isOnlyLetters(firstName) && !Utils.isOnlyLetters(lasstName)){
            throw new ExceptionManager.InvalidValueException("First or last name");
        }
        return true;
    }

    @Override
    public boolean validateEmail(String email) throws Exception {
        if (!Utils.isAValidEmail(email)){
            throw new ExceptionManager.InvalidValueException("Email");
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) throws Exception {
        if(!Utils.isValidPassword(password)){
            throw new ExceptionManager.InvalidPasswordFormatException();
        }
        return true;
    }

    @Override
    public boolean validatePercentage(int percentage) throws Exception {
        if (!Utils.isNumeric(String.valueOf(percentage)) ||
        percentage < Utils.MIN_PERCENTAGE || percentage > Utils.MAX_PERCENTAGE){
            throw new ExceptionManager.InvalidRangeException("Percentage", Utils.MIN_PERCENTAGE, Utils.MAX_PERCENTAGE);
        }
        return true;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }


}
