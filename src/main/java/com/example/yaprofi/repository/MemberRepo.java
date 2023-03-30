package com.example.yaprofi.repository;

import com.example.yaprofi.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<MemberEntity, Integer> {
}
