package com.rudy.ryanto.socialmedia.user.management.repository;

import com.rudy.ryanto.socialmedia.user.management.domain.MasterUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterUserDetailsRepository extends JpaRepository<MasterUserDetails,Integer> {
}
