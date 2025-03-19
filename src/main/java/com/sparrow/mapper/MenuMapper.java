package com.sparrow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sparrow.entity.Menu;

import java.util.List;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/19 10:32
 **/
public interface MenuMapper extends BaseMapper<Menu> {

	List<String> selectPermsByUserId(Long userId);
}
