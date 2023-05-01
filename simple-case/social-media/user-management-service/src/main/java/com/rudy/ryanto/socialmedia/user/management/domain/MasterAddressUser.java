package com.rudy.ryanto.socialmedia.user.management.domain;

import com.rudy.ryanto.common.domain.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity(name = "master_address_user")
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MasterAddressUser extends AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address",nullable = false)
    private Long idAddress;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "province")
    private String province;
    @Column(name = "country")
    private String country;
    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne
    private MasterUser masterUser;
}
