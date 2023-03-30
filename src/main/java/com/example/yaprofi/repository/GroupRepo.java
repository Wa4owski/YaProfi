package com.example.yaprofi.repository;

import com.example.yaprofi.entity.GroupEntity;
import com.example.yaprofi.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepo extends JpaRepository<GroupEntity, Integer> {
}
