package com.rudy.ryanto.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.Date;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuditTrail {
    @Column
    private String createBy;
    @Column
    private String updateBy;
    @Column
    private Date createDate;
    @Column
    private Date updateDate;
}