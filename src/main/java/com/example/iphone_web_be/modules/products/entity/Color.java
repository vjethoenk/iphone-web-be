package com.example.iphone_web_be.modules.products.entity;

import com.example.iphone_web_be.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(
        name = "colors",
        indexes = {
                @Index(name = "idx_color_name", columnList = "name")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Color extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    String name;

    @Column(name = "hex_code", length = 20)
    String hexCode;

    @Column(name = "display_order")
    int displayOrder;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    boolean active = true;
}