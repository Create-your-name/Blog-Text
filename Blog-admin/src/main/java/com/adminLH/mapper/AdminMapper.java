package com.adminLH.mapper;

import com.adminLH.pojo.Admin;
import com.adminLH.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminMapper  extends BaseMapper<Admin> {
    @Select(" select * from ms_permission where id in (select permission_id from ms_admin_permission where admin_id=#{adminId})")

    List<Permission> findPermissionByAdminId(Long adminId);
}
