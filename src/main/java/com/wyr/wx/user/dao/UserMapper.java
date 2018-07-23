package com.wyr.wx.user.dao;

import com.wyr.wx.user.domain.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yrwu
 * @since 2018-04-23
 */
public interface UserMapper extends BaseMapper<User> {
	
	/**
	 * 
	 * @Title  getUserByOpenid 
	 * @author  yrwu
	 * @Date 2018年4月23日 下午1:49:04
	 * @param name
	 * @return  User
	 */
	User getUserByOpenid(String openid);

}
