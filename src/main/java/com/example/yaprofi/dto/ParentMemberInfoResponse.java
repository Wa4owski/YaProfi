package com.example.yaprofi.dto;

import com.example.yaprofi.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ParentMemberInfoResponse {
    private Integer id;
    private String name;
    private String wish;
    private ChildMemberInfoResponse recipient;

    public ParentMemberInfoResponse(MemberEntity member) {
        this.id = member.getId();
        this.name = member.getName();
        this.wish = member.getWish();
        this.recipient = (member.getRecipient() == null ? null : new ChildMemberInfoResponse(member.getRecipient()));
    }
}
