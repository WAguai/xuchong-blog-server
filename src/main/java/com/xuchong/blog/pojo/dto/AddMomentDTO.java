package com.xuchong.blog.pojo.dto;

import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class AddMomentDTO {
    private List<String> images;
    private String content;
}
