package edu.xcj.service.impl;

import edu.xcj.entity.Blog;
import edu.xcj.mapper.BlogMapper;
import edu.xcj.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuecj
 * @since 2022-04-27
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
