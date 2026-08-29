package com.jamesaworo.stocky.entity.settings;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.jamesaworo.stocky.core.constants.Table.SETTING_DASHBOARD;

@Entity
@Table(name = SETTING_DASHBOARD)
public class SettingDashboard extends Setting {

    public SettingDashboard() {
    }


    public SettingDashboard(SettingObj obj) {
        super(obj);
    }
}