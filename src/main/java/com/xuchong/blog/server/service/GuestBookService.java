package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddGuestBookMessageDTO;


public interface GuestBookService {

    Result<?> addMessage(AddGuestBookMessageDTO addGuestBookMessageDTO);

    Result<?> getMessages(Integer page,Integer pageSize);

    Result<?> deleteGuestBook(Integer guestBookId);
}
