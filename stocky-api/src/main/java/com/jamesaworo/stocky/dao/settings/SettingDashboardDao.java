package com.jamesaworo.stocky.dao.settings;

import com.jamesaworo.stocky.entity.settings.SettingDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Aworo James
 * @since 4/20/23
 */
public interface SettingDashboardDao extends JpaRepository<SettingDashboard, Long> {

    Optional<SettingDashboard> findBySettingKey(String key);

    @Transactional
    @Modifying
    @Query(value = "UPDATE SettingDashboard s SET s.settingValue = :value where s.settingKey = :key")
    int updateByKey(String key, String value);
}