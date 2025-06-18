package com.xuchong.blog.pojo.entity;

import lombok.Data;

import java.util.List;

@Data
public class GuestComments {
    private Integer guestBookId;
    private List<OneGuestComment> guestComments;
}
