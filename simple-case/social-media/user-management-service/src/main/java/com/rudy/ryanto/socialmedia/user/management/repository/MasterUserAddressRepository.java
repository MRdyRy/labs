package com.rudy.ryanto.socialmedia.user.management.repository;

import com.rudy.ryanto.socialmedia.user.management.domain.MasterAddressUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterUserAddressRepository extends JpaRepository<MasterAddressUser,Long> {
}
