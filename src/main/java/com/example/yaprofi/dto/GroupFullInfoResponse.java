package com.example.yaprofi.dto;

import com.example.yaprofi.entity.GroupEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class GroupFullInfoResponse {
    private Integer id;
    private String name;
    private String description;
    private List<ParentMemberInfoResponse> participants;

    public GroupFullInfoResponse(GroupEntity group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.participants = group.getMembers().stream()
                .map(ParentMemberInfoResponse::new).collect(Collectors.toList());
    }
}
