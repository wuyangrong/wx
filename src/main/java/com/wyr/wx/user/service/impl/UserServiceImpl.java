package com.wyr.wx.user.service.impl;

import com.wyr.wx.user.domain.User;
import com.wyr.wx.user.dao.UserMapper;
import com.wyr.wx.user.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yrwu123
 * @since 2018-04-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
