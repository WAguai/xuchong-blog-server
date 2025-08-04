package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddMomentCommentDTO;
import com.xuchong.blog.pojo.dto.AddMomentDTO;

public interface MomentService {
    Result<?> getMoments(Integer currentPage,Integer pageSize);

    Result<?> getMomentDetails(Integer id);

    Result<?> likeOrDislike(Integer momentId);

    Result<?> addMomentComment(AddMomentCommentDTO addMomentCommentDTO);

    Result<?> addMoment(AddMomentDTO addMomentDTO);
    
    Result<?> updateMoment(AddMomentDTO addMomentDTO);
}
