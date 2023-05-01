package com.rudy.ryanto.socialmedia.user.management.service;

import com.rudy.ryanto.socialmedia.user.management.domain.MasterAddressUser;
import com.rudy.ryanto.socialmedia.user.management.domain.MasterUser;
import com.rudy.ryanto.socialmedia.user.management.domain.MasterUserDetails;
import com.rudy.ryanto.socialmedia.user.management.domain.UserDto;
import com.rudy.ryanto.socialmedia.user.management.exception.UserManagementException;
import com.rudy.ryanto.socialmedia.user.management.repository.MasterUserAddressRepository;
import com.rudy.ryanto.socialmedia.user.management.repository.MasterUserDetailsRepository;
import com.rudy.ryanto.socialmedia.user.management.repository.MasterUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.rudy.ryanto.socialmedia.user.management.mapper.MapperDtoAddress.mapperDtoAddress;
import static com.rudy.ryanto.socialmedia.user.management.mapper.MapperDtoDetail.mapperDtoToDetails;

@Service
@Slf4j
public class UserManagementNonAuthService implements UserManagementNonAuth{

    @Autowired
    private MasterUserRepository masterUserRepository;

    @Autowired
    private MasterUserDetailsRepository masterUserDetailsRepository;

    @Autowired
    private MasterUserAddressRepository masterUserAddressRepository;

    @Transactional
    @Override
    public <T> void addUser(T user) {
        log.debug("process to save data : {}",user);
        try{
            UserDto userDto = (UserDto) user;
            var saveData = masterUserRepository.save(
                    MasterUser.builder()
                    .userName(userDto.getUserName())
                    .build()
            );

            MasterUserDetails masterUserDetails = mapperDtoToDetails(userDto, saveData);
            addDetailUser(masterUserDetails);

            MasterAddressUser masterAddressUser = mapperDtoAddress(userDto, saveData);
            addAddressUser(masterAddressUser);
        }catch (Exception e){
            log.error("Failed to process save data : ",e);
            throw new UserManagementException(e.getMessage());
        }
    }

    @Override
    public <T> Boolean deleteUser(T user) {
        return null;
    }

    @Override
    public <T> T updateUser(T user) {
        return null;
    }

    @Override
    public <T> List<?> searchUser(T user) {
        return null;
    }

    @Override
    public <T> Integer getNumberOfUser(T user) {
        return null;
    }

    @Override
    public Integer countAllUser() {
        return null;
    }

    @Override
    public <U> void addAddressUser(U address) {
        MasterAddressUser masterAddressUser = (MasterAddressUser) address;
        masterUserAddressRepository.save(masterAddressUser);
    }

    @Override
    public <T> void addDetailUser(T detail) {
        MasterUserDetails masterUserDetails = (MasterUserDetails) detail;
        masterUserDetailsRepository.save(masterUserDetails);
    }
}
