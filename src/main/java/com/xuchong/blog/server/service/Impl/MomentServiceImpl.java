package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuchong.blog.common.context.BaseContext;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddMomentCommentDTO;
import com.xuchong.blog.pojo.dto.AddMomentDTO;
import com.xuchong.blog.pojo.entity.*;
import com.xuchong.blog.pojo.entity.db.*;
import com.xuchong.blog.pojo.vo.AddMomentCommentVO;
import com.xuchong.blog.pojo.vo.GetMomentDetailsVO;
import com.xuchong.blog.pojo.vo.GetMomentVO;
import com.xuchong.blog.server.mapper.*;
import com.xuchong.blog.server.service.MomentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MomentServiceImpl extends ServiceImpl<MomentMapper, Moment>  implements MomentService {
    @Resource
    private MomentMapper momentMapper;
    @Resource
    private MomentCommentMapper momentCommentMapper;
    @Resource
    private MomentLikesMapper momentLikesMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<?> getMoments(Integer currentPage,Integer pageSize) {
        // 1. 分页查询说说基础信息
        Page<OneMoment> page = new Page<>(currentPage, pageSize);
        IPage<OneMoment> momentPage = momentMapper.selectMomentPage(page, null);
        List<OneMoment> moments = momentPage.getRecords();
        log.info("momentPage:{}",momentPage.getTotal());

        // 2. 提取说说ID列表
        List<Integer> momentIds = momentPage.getRecords().stream()
                .map(OneMoment::getId)
                .collect(Collectors.toList());
        if(momentIds.isEmpty()){
            return Result.success("没有说说");
        }

        // 3. 批量查询关联数据（避免N+1查询）
        Map<Integer, MomentComments> commentsMap = momentCommentMapper.selectCommentsByMomentIds(momentIds);
        Map<Integer, MomentLikes> likeCountMap = momentLikesMapper.selectLikesByMomentIds(momentIds);

        // 4. 组装VO对象
        moments.forEach(moment -> {
            MomentComments commentObj = commentsMap.get(moment.getId()); // 先获取对象
            if (commentObj != null) { // 关键检查！
                moment.setComments(commentObj.getMomentComments());
            } else {
                moment.setComments(Collections.emptyList()); // 设置空列表避免后续NPE
            }
            if(likeCountMap.get(moment.getId()) != null)
                moment.setLikes(likeCountMap.get(moment.getId()).getMomentLikes());
            else
                moment.setLikes(Collections.emptyList());

        });

        // 5. 返回最终结果
        GetMomentVO getMomentVO = new GetMomentVO();
        getMomentVO.setTotalMoments(momentPage.getTotal());
        getMomentVO.setMoments(moments);
        return Result.success(getMomentVO);
    }

    @Override
    public Result<?> getMomentDetails(Integer id) {
        // 1. 根据ID查询说说基础信息
        GetMomentDetailsVO getMomentDetailsVO = new GetMomentDetailsVO();
        Moment moment = momentMapper.selectById(id);

        log.info("说说id，{}",id);
        if (moment == null) {
            return Result.error("说说不存在或已被删除");
        }
        BeanUtils.copyProperties(moment, getMomentDetailsVO);

        // 2. 创建单元素ID列表（复用批量查询逻辑）
        List<Integer> momentIds = Collections.singletonList(id);

        // 3. 批量查询关联数据（复用现有方法）
        Map<Integer, MomentComments> commentsMap = momentCommentMapper.selectCommentsByMomentIds(momentIds);
        Map<Integer, MomentLikes> likeCountMap = momentLikesMapper.selectLikesByMomentIds(momentIds);
        User user = userMapper.selectById(moment.getUserId());

        // 4. 组装关联数据（带空值保护）
        MomentComments commentObj = commentsMap.get(id);
        if (commentObj != null) {
            getMomentDetailsVO.setComments(commentObj.getMomentComments());
        } else {
            getMomentDetailsVO.setComments(Collections.emptyList());
        }
        
        MomentLikes likeObj = likeCountMap.get(id);
        if (likeObj != null) {
            getMomentDetailsVO.setLikes(likeObj.getMomentLikes());
        } else {
            getMomentDetailsVO.setLikes(Collections.emptyList());
        }
        getMomentDetailsVO.setNickName(user.getNickName());

        return Result.success(getMomentDetailsVO);
    }

    @Override
    public Result<?> likeOrDislike(Integer momentId) {
        Integer userId = BaseContext.getCurrentId();
        QueryWrapper<MomentLike> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);//相当于where id=1
        wrapper.eq("moment_id",momentId);//相当于where id=1
        MomentLike momentLike = momentLikesMapper.selectOne(wrapper);
        log.info("userId:{}",userId);
        // 如果为null，点赞，插入一条数据
        if (momentLike == null) {
            MomentLike momentLike1 = new MomentLike();
            momentLike1.setMomentId(momentId);
            momentLike1.setCreateTime(LocalDateTime.now());
            momentLike1.setUserId(userId);
            momentLikesMapper.insert(momentLike1);
            return Result.success(momentLike1);
        }
        // 如果存在，则删除该信息
        else{
            momentLikesMapper.deleteById(momentLike.getId());
            return Result.success("取消点赞成功");
        }
    }

    @Override
    public Result<?> addMomentComment(AddMomentCommentDTO addMomentCommentDTO) {
        Integer userId = BaseContext.getCurrentId();
        
        MomentComment momentComment = new MomentComment();
        momentComment.setMomentId(addMomentCommentDTO.getMomentId());
        momentComment.setUserId(userId);
        momentComment.setContent(addMomentCommentDTO.getContent());
        momentComment.setCreateTime(LocalDateTime.now());
        momentComment.setUpdateTime(LocalDateTime.now());
        momentCommentMapper.insert(momentComment);
        User user = userMapper.selectById(userId);
        AddMomentCommentVO addMomentCommentVO = new AddMomentCommentVO();
        BeanUtils.copyProperties(momentComment, addMomentCommentVO);
        addMomentCommentVO.setNickName(user.getNickName());
        return Result.success(addMomentCommentVO);
    }

    @Override
    public Result<?> addMoment(AddMomentDTO addMomentDTO) {
        Integer userId = BaseContext.getCurrentId();
        Moment moment = new Moment();
        moment.setUserId(userId);
        moment.setTitle(addMomentDTO.getTitle());
        moment.setContent(addMomentDTO.getContent());
//        moment.setImages(""); // 不再单独存储图片，内容中包含Markdown格式的图片
        moment.setCreateTime(LocalDateTime.now());
        moment.setUpdateTime(LocalDateTime.now());
        
        momentMapper.insert(moment);
        
        return Result.success(moment);
    }

    @Override
    public Result<?> updateMoment(AddMomentDTO addMomentDTO) {
        Integer userId = BaseContext.getCurrentId();
        
        // 检查说说是否存在
        Moment existingMoment = momentMapper.selectById(addMomentDTO.getId());
        if (existingMoment == null) {
            return Result.error("说说不存在或已被删除");
        }
        
        // 检查权限 - 只能更新自己的说说
        if (!existingMoment.getUserId().equals(userId)) {
            return Result.error("无权更新他人的说说");
        }
        
        existingMoment.setTitle(addMomentDTO.getTitle());
        existingMoment.setContent(addMomentDTO.getContent());
        existingMoment.setUpdateTime(LocalDateTime.now());
        
        momentMapper.updateById(existingMoment);
        
        return Result.success(existingMoment);
    }
}