package com.jamesaworo.stocky.entity.settings;


import javax.persistence.Entity;
import javax.persistence.Table;

import static com.jamesaworo.stocky.core.constants.Table.SETTING_STOCK;

@Entity
@Table(name = SETTING_STOCK)
public class SettingStock extends Setting {

    public SettingStock() {
    }

    public SettingStock(SettingObj obj) {
        super(obj);
    }
}