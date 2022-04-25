package com.toptal.backend.controller.dto.response;

import com.toptal.backend.data.model.User;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class UserPageListResponse {
    private List<User> userList;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public static UserPageListResponse from(Page<User> userPage) {
        UserPageListResponse userPageListResponse = new UserPageListResponse();
        userPageListResponse.setUserList(userPage.getContent());
        userPageListResponse.setPageNumber(userPage.getNumber());
        userPageListResponse.setTotalPages(userPage.getTotalPages());
        userPageListResponse.setPageSize(userPage.getSize());
        userPageListResponse.setTotalElements(userPage.getTotalElements());

        return userPageListResponse;
    }
}
