package com.rudy.ryanto.socialmedia.user.management.domain;

import com.rudy.ryanto.common.domain.AuditTrail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "master_user_details")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MasterUserDetails extends AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDetail;

    @OneToOne
    private MasterUser masterUser;

    @Column(name = "DOB")
    private Date DOB;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_user")
    private StatusUser status;

}
