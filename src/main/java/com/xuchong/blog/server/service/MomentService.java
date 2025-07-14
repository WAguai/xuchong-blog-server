package com.xuchong.blog.server.service;

import com.xuchong.blog.common.result.Result;

public interface MomentService {
    Result<?> getMoments(Integer currentPage,Integer pageSize);

    Result<?> getMomentDetails(Integer id);

    Result<?> likeOrDislike(Integer momentId);
}
