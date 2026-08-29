package com.jamesaworo.stocky.entity.settings;


import javax.persistence.Entity;
import javax.persistence.Table;

import static com.jamesaworo.stocky.core.constants.Table.SETTING_TAX;

@Entity
@Table(name = SETTING_TAX)
public class SettingTax extends Setting {

    public SettingTax() {
    }

    public SettingTax(SettingObj obj) {
        super(obj);
    }
}