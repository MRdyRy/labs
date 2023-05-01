package com.rudy.ryanto.socialmedia.user.management.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userName;
    private MasterUserDetails userDetails;
    private MasterAddressUser userAddress;
    private String clientId;
}
