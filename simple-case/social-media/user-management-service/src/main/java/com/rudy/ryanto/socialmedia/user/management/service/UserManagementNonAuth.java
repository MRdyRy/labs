package com.rudy.ryanto.socialmedia.user.management.service;

import java.util.List;

public interface UserManagementNonAuth {

    <T> void addUser(T user);

    <T> Boolean deleteUser(T user);

    <T> T updateUser(T user);

    <T> List<?> searchUser(T user);

    <T> Integer getNumberOfUser(T user);

    Integer countAllUser();

    <T> void addAddressUser(T address);

    <T> void addDetailUser(T detail);
}
