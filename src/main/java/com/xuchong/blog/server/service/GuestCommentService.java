package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddGuestCommentDTO;

public interface GuestCommentService {
    Result<?> addComment(AddGuestCommentDTO addGuestCommentDTO);

    Result<?> deleteGuestComment(Integer guestCommentId);
}
