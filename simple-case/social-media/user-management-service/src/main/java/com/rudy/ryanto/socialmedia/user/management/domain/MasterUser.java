package com.rudy.ryanto.socialmedia.user.management.domain;

import com.rudy.ryanto.common.domain.AuditTrail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "master_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MasterUser extends AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "user_name",nullable = false)
    private String userName;



}
