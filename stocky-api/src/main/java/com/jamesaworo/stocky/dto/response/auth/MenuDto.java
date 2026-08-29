package com.jamesaworo.stocky.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.auth.enums.AppModuleRoute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDto {
    private String key;
    private String text;
    private boolean group;
    private String link;
    private MenuIconDto icon;
    private boolean disabled;
    private boolean hide;
    private String[] acl;
    private List<MenuDto> children = new ArrayList<>();

    public MenuDto(String text, boolean group) {
        this.text = text;
        this.group = group;
    }

    private static MenuDto parent(String text, MenuIconDto icon) {
        MenuDto menuItem = new MenuDto();
        menuItem.text = text;
        menuItem.group = true;
        menuItem.icon = icon;
        return menuItem;
    }

    private static MenuDto child(String text, String link) {
        MenuDto menuItem = new MenuDto();
        menuItem.text = text;
        menuItem.link = link;
        return menuItem;
    }

    public static MenuDto parentWithChildren(
            String parentText,
            String parentIconValue,
            Map<String, String> permissions,
            List<AppModuleRoute> pages
    ) {
        MenuDto parent = parent(parentText, new MenuIconDto("icon", parentIconValue));
        if (pages != null && pages.size() > 0) {
            pages.forEach(route -> {
                if (!isEmpty(permissions) && permissions.containsKey(route.getPermission())) {
                    MenuDto.appendChild(parent, child(route.getText(), route.getRoute()));
                }
            });
        }
        return parent;
    }

    public static void appendChild(MenuDto parent, MenuDto child) {
        List<MenuDto> children = parent.children;
        if (children != null && children.size() > 0) {
            parent.children.add(child);
        } else {
            parent.children = new ArrayList<>();
            parent.children.add(child);
        }
    }
}
