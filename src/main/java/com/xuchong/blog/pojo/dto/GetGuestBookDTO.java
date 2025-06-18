package com.xuchong.blog.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
public class GetGuestBookDTO {
    private Integer page;
    private Integer pageSize;
}




