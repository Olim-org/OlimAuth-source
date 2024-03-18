package com.olim.authservice.repository;


import com.olim.authservice.entity.EndUser;
import com.olim.authservice.enumeration.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, UUID> {
    Optional<EndUser> findByEmail(String email);
    Optional<EndUser> findByNickname(String username);
    Optional<EndUser> findByPhoneNumber(String phoneNumber);
    Optional<EndUser> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
    Boolean existsByEmail(String email);
}
