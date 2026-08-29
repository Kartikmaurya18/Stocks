package com.jamesaworo.stocky.entity.auth.enums;

import lombok.Data;

@Data
public class AppModuleRoute {
    private String text;
    private String permission;
    private String route;

    public AppModuleRoute(String text, String permission, String route) {
        this.text = text;
        this.permission = permission;
        this.route = route;
    }
}
