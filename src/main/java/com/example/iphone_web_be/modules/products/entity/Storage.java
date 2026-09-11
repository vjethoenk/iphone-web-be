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
        name = "storages",
        indexes = {
                @Index(name = "idx_storage_capacity", columnList = "capacity_gb")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Storage extends BaseEntity {

    @Column(nullable = false, unique = true, length = 20)
    String name;

    @Column(name = "capacity_gb", nullable = false)
    int capacityGb;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    boolean active = true;
}