package com.liuyubo.qs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyubo.qs.db.POJO.Role;
import com.liuyubo.qs.service.RoleService;
import com.liuyubo.qs.db.DAO.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author kdrsi
* @description 针对表【tb_role(角色表)】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {

}




