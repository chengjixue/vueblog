package edu.xcj.service.impl;

import edu.xcj.entity.User;
import edu.xcj.mapper.UserMapper;
import edu.xcj.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
