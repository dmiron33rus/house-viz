package com.houseviz.houseviz.user.domain;

import com.houseviz.houseviz.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    private String phone;
    private String email;
    private String address;

    @Column(nullable = false)
    private boolean active = true;
}
