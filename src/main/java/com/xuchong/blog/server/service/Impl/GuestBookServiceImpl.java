package com.xuchong.blog.server.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuchong.blog.common.result.Result;
import com.xuchong.blog.pojo.dto.AddGuestBookMessageDTO;
import com.xuchong.blog.pojo.entity.OneMoment;
import com.xuchong.blog.pojo.entity.db.GuestBook;
import com.xuchong.blog.pojo.entity.db.GuestComment;
import com.xuchong.blog.pojo.entity.GuestComments;
import com.xuchong.blog.pojo.entity.OneGuestBook;
import com.xuchong.blog.pojo.vo.AddGuestBookMessageVO;
import com.xuchong.blog.pojo.vo.GetGuestBookVO;
import com.xuchong.blog.server.mapper.GuestBookMapper;
import com.xuchong.blog.server.mapper.GuestCommentMapper;
import com.xuchong.blog.server.service.GuestBookService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Repository
public class GuestBookServiceImpl extends ServiceImpl<GuestBookMapper, GuestBook> implements GuestBookService  {
    @Resource
    private GuestBookMapper guestBookMapper;
    @Resource
    private GuestCommentMapper guestCommentMapper;

    @Override
    public Result<?> addMessage(AddGuestBookMessageDTO addGuestBookMessageDTO) {
        GuestBook guestBook = new GuestBook();
        BeanUtils.copyProperties(addGuestBookMessageDTO, guestBook);
        guestBook.setCreateTime(LocalDateTime.now());
        guestBook.setUpdateTime(LocalDateTime.now());
        save(guestBook);
        AddGuestBookMessageVO addGuestBookMessageVO = new AddGuestBookMessageVO();
        BeanUtils.copyProperties(guestBook, addGuestBookMessageVO);
        log.info(addGuestBookMessageVO.toString());
        addGuestBookMessageVO.setNickName(addGuestBookMessageDTO.getNickName());
        addGuestBookMessageVO.setGuestComments(Collections.emptyList());

        return Result.success(addGuestBookMessageVO);
    }

    @Override
    public Result<?> getMessages(Integer currentPage,Integer pageSize) {
        log.info("获取留言板");
        // 1. 分页查询留言
        Page<OneGuestBook> page = new Page<>(currentPage, pageSize);
        Page<OneGuestBook> guestBookPage = guestBookMapper.selectGuestBookPage(page,null);
        List<OneGuestBook> guestBooks = guestBookPage.getRecords();

        // 2. 批量查询关联评论
        if (!guestBooks.isEmpty()) {
            List<Integer> guestBookIds = guestBooks.stream()
                    .map(OneGuestBook::getId)
                    .collect(Collectors.toList());

            Map<Integer, GuestComments> commentsMap =
                    guestCommentMapper.selectCommentsByGuestBookIds(guestBookIds);

            log.info("guestBook:{}", guestBooks);
            log.info("commentsMap:{}", commentsMap);
            // 3. 组装评论到留言
            guestBooks.forEach(book -> {
                GuestComments commentObj = commentsMap.get(book.getId()); // 先获取对象
                if (commentObj != null) { // 关键检查！
                    book.setGuestComments(commentObj.getGuestComments());
                } else {
                    book.setGuestComments(Collections.emptyList()); // 设置空列表避免后续NPE
                }
            });
        }
         // 4. 封装返回结果
        GetGuestBookVO vo = new GetGuestBookVO();
        vo.setGuestBooks(guestBooks);
        vo.setTotalEntries(guestBookPage.getTotal());
        log.info("一共{}条",guestBookPage.getTotal());
        return Result.success(vo);
    }

    @Override
    public Result<?> deleteGuestBook(Integer guestBookId) {
        // 1. 删除该留言板的所有评论
        LambdaQueryWrapper<GuestComment> commentWrapper = new LambdaQueryWrapper<>();
        commentWrapper.eq(GuestComment::getGuestBookId, guestBookId);
        guestCommentMapper.delete(commentWrapper);

        // 2. 删除留言板
        if(guestBookMapper.deleteById(guestBookId) > 0){
            return Result.success("删除留言成功");
        }
        return Result.error("留言不存在");
    }

}
