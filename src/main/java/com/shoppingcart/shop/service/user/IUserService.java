package com.shoppingcart.shop.service.user;

import com.shoppingcart.shop.dto.UserDto;
import com.shoppingcart.shop.model.User;
import com.shoppingcart.shop.request.CreateUserRequest;
import com.shoppingcart.shop.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}
