package com.jamesaworo.stocky.dao.settings;

import com.jamesaworo.stocky.entity.settings.SettingStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Aworo James
 * @since 4/20/23
 */
@Repository
public interface SettingStockDao extends JpaRepository<SettingStock, Long> {
    Optional<SettingStock> findBySettingKey(String key);

    @Transactional
    @Modifying
    @Query(value = "UPDATE SettingStock s SET s.settingValue = :value where s.settingKey = :key")
    int updateByKey(String key, String value);
}