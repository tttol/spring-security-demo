package com.example.springsecuritydemo.repository;

import com.example.springsecuritydemo.mbgenerate.crud.CustomLoginUserMapper;
import com.example.springsecuritydemo.mbgenerate.crud.LoginUserDynamicSqlSupport;
import com.example.springsecuritydemo.mbgenerate.crud.LoginUserMapper;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUserAndRoleNameEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoginUserRepository {
    private final LoginUserMapper loginUserMapper;
    private final CustomLoginUserMapper customLoginUserMapper;

    public List<LoginUserAndRoleNameEntity> selectLoginUserAndRoleNameRecordByEmail(final String email) {
        return customLoginUserMapper.selectLoginUserRolesEntity(email);
    }

    public void simpleWhere() {
        log.info("----- simple where -----");
        loginUserMapper.select(c -> c.where(LoginUserDynamicSqlSupport.id, SqlBuilder.isEqualTo(1)))
                .forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }

    public void complexWhere() {
        log.info("----- complex where -----");
        loginUserMapper.select(c -> c.where(LoginUserDynamicSqlSupport.email, SqlBuilder.isLike("%@example.com"))
                .and(LoginUserDynamicSqlSupport.name, SqlBuilder.isLike("管理%"))
        ).forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }

    public void useCustomLoginUserMapper() {
        log.info("----- use custom mapper -----");
        customLoginUserMapper.selectAll()
                .forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
        customLoginUserMapper.selectAllBySqlFile()
                .forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }
}
