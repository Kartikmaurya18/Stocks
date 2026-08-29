package com.jamesaworo.stocky.entity.auth;

import com.jamesaworo.stocky.entity.auth.enums.AppModuleEnum;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static com.jamesaworo.stocky.core.constants.Table.AUTH_PERMISSION;

@Data
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = AUTH_PERMISSION)
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private AppModuleEnum module;

    public Permission(String name, AppModuleEnum module) {
        this.name = name;
        this.module = module;
    }

    public Permission() {
    }

    public Permission(Long id) {
        this.id = id;
    }
}
