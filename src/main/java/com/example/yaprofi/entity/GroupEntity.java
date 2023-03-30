package com.example.yaprofi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group_")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<MemberEntity> members = new ArrayList<>();

    public void addMember(MemberEntity member){
        members.add(member);
        member.setGroup(this);
    }

    public boolean deleteMember(MemberEntity member){
        if(member.getGroup().equals(this)){
            member.setGroup(null);
            return members.remove(member);
        }
        return false;
    }

}
