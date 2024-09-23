package com.maxim.spring_security_rest_api_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maxim.spring_security_rest_api_app.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String email;


    public User toUser() {

            User user = new User();
            user.setId(id);
            user.setUserName(username);
            user.setEmail(email);
            return user;
        }

    public static UserDto fromUser(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        List<EventDto> eventDtoList = CollectionUtils.isEmpty(user.getEventList()) ?
                Collections.emptyList() :
                user.getEventList().stream().map(EventDto::fromEvent).collect(Collectors.toList());
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUserName());
            userDto.setEmail(user.getEmail());
            return userDto;
    }
    public static List<UserDto> toUserDtos(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: users) {
            userDtoList.add(fromUser(user));
        }
        return userDtoList;
    }

}
