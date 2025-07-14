package com.xuchong.blog.pojo.vo;

import com.xuchong.blog.pojo.entity.OneGuestBook;
import lombok.Data;

import java.util.List;

@Data
public class GetGuestBookVO{
    private Long totalEntries;      // 总记录数
    private List<OneGuestBook> guestBooks;
}
