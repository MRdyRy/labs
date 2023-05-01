package com.rudy.ryanto.socialmedia.user.management.mapper;

import com.rudy.ryanto.socialmedia.user.management.domain.MasterUser;
import com.rudy.ryanto.socialmedia.user.management.domain.MasterUserDetails;
import com.rudy.ryanto.socialmedia.user.management.domain.UserDto;

import java.util.Date;

public class MapperDtoDetail {
    public static MasterUserDetails mapperDtoToDetails(UserDto userDto, MasterUser saveData) {
        return MasterUserDetails.builder()
                .DOB(userDto.getUserDetails().getDOB())
                .status(userDto.getUserDetails().getStatus())
                .gender(userDto.getUserDetails().getGender())
                .masterUser(saveData)
                .createDate(new Date())
                .createBy(userDto.getClientId())
                .build();
    }
}
