package edu.xcj.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.xcj.common.lang.Result;
import edu.xcj.entity.Blog;
import edu.xcj.service.BlogService;
import edu.xcj.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.LocaleData;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuecj
 * @since 2022-04-27
 */
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") int currentPage) {
        Page page = new Page(currentPage,1);
        IPage pageDate = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageDate);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name="id") Long id) {
        Blog blog=blogService.getById(id);
        Assert.notNull(blog,"博客不存在");
        return Result.succ(blog);

    }
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result list(@Validated @RequestBody Blog blog) {
        Blog temp=null;
        if (blog.getId()!=null){
            temp=blogService.getById(blog.getId());
          Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"，没有权限修改");
        }else {
            temp=new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return Result.succ(null);

    }


}
