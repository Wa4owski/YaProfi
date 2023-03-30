package com.example.yaprofi.dto;

import com.example.yaprofi.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupInfoResponse {
    private Integer id;
    private String name;
    private String description;

    public GroupInfoResponse(GroupEntity group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
    }
}
