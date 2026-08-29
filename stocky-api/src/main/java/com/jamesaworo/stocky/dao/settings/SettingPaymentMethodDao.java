package com.jamesaworo.stocky.dao.settings;

import com.jamesaworo.stocky.entity.settings.SettingPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Aworo James
 * @since 4/20/23
 */
public interface SettingPaymentMethodDao extends JpaRepository<SettingPaymentMethod, Long> {
    Optional<SettingPaymentMethod> findByTitle(String title);
}