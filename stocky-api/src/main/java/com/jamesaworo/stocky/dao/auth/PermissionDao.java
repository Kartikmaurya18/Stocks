package com.jamesaworo.stocky.dao.auth;

import com.jamesaworo.stocky.entity.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long> {
}
