package com.jamesaworo.stocky.entity.settings;


import javax.persistence.Entity;
import javax.persistence.Table;

import static com.jamesaworo.stocky.core.constants.Table.SETTING_PEOPLE;

@Entity
@Table(name = SETTING_PEOPLE)
public class SettingPeople extends Setting {

    public SettingPeople() {
    }


    public SettingPeople(SettingObj obj) {
        super(obj);
    }
}