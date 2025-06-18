package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddGuestCommentDTO;
import com.xuchong.blog.pojo.entity.GuestComment;

import com.xuchong.blog.pojo.vo.AddGuestCommentVO;
import com.xuchong.blog.server.mapper.GuestCommentMapper;
import com.xuchong.blog.server.service.GuestCommentService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GuestCommentServiceImpl extends ServiceImpl<GuestCommentMapper, GuestComment> implements GuestCommentService {
    @Resource
    private GuestCommentMapper guestCommentMapper;

    @Override
    public Result<?> addComment(AddGuestCommentDTO addGuestCommentDTO) {
        GuestComment guestComment = new GuestComment();
        BeanUtils.copyProperties(addGuestCommentDTO, guestComment);
        guestComment.setCreateTime(LocalDateTime.now());
        guestComment.setUpdateTime(LocalDateTime.now());
        save(guestComment);
        AddGuestCommentVO addGuestCommentVO = new AddGuestCommentVO();
        BeanUtils.copyProperties(guestComment, addGuestCommentVO);
        addGuestCommentVO.setNickName(addGuestCommentDTO.getNickName());
        return Result.success(addGuestCommentVO);
    }

    @Override
    public Result<?> deleteGuestComment(Integer guestCommentId) {
         if(guestCommentMapper.deleteById(guestCommentId) > 0){
             return Result.success("删除留言板评论成功");
         }
        return Result.error("留言板评论不存在");
    }
}
