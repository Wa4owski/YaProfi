package com.example.yaprofi.dto;

import com.example.yaprofi.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChildMemberInfoResponse {
    private Integer id;
    private String name;
    private String wish;

    public ChildMemberInfoResponse(MemberEntity recipient) {
        this.id = recipient.getId();
        this.name = recipient.getName();
        this.wish = recipient.getWish();
    }
}
