package com.malynovsky.dao;

import com.malynovsky.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan on 08.04.2017.
 */
public interface RoleDao extends JpaRepository<Role, Long> {
}
