package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * This method validates the userDto data
     * @param userDto transfer all user data
     * @throws Exception when user is not valid
     */
    void validateUserDTOfields(UserDto userDto) throws Exception;

    /**
     * This method converts an userDto class to an user
     * @param userDto with all user data
     * @return An user with the userDto data
     * @throws Exception when user is not valid
     */
    User convertUserDTOfieldsToUser(UserDto userDto) throws Exception;

    /**
     * This method finds an user by email and password
     * @param userDto with all user data
     * @return An user
     * @throws Exception when user is not valid
     */
    User findAnUserByEmailAndPassword(UserDto userDto) throws Exception;

    /**
     * This method finds an user by id
     * @param id from an existing user
     * @return An user
     * @throws Exception when user is not valid
     */
    User findUserById(Long id) throws Exception;

    /**
     * This method creates a new user
     * @param userDto with all user data
     * @return An user
     * @throws Exception when user is not valid
     */
    User saveUser(UserDto userDto) throws Exception;

    /**
     * This method modifies an existing user
     * @param userDto with all user data
     * @return A modified user
     * @throws Exception if user data is not valid
     */
    User modifyUser(UserDto userDto) throws Exception;

    /**
     * This method deletes an user by id
     * @param id from an existing user
     * @throws Exception when user is not valid
     */
    void deleteUserById(Long id) throws Exception;
}
