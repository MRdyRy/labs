package com.rudy.ryanto.socialmedia.user.management.mapper;

import com.rudy.ryanto.socialmedia.user.management.domain.MasterAddressUser;
import com.rudy.ryanto.socialmedia.user.management.domain.MasterUser;
import com.rudy.ryanto.socialmedia.user.management.domain.UserDto;

public class MapperDtoAddress {
    public static MasterAddressUser mapperDtoAddress(UserDto userDto, MasterUser saveData) {
        return MasterAddressUser.builder()
                .streetName(userDto.getUserAddress().getStreetName())
                .country(userDto.getUserAddress().getCountry())
                .province(userDto.getUserAddress().getProvince())
                .postalCode(userDto.getUserAddress().getPostalCode())
                .masterUser(saveData)
                .build();
    }
}
