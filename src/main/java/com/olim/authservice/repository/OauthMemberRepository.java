package com.olim.authservice.repository;

import com.olim.authservice.entity.OauthMember;
import com.olim.authservice.oauth2.OauthId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OauthMemberRepository extends JpaRepository<OauthMember, UUID> {

    Optional<OauthMember> findByOauthId(OauthId oauthId);
}

