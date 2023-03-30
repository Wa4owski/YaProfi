package com.example.yaprofi.controller;

import com.example.yaprofi.dto.*;
import com.example.yaprofi.service.AppService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    AppService service;

    @PostMapping(path = "/group")
    public Integer createGroup(@Valid @RequestBody GroupCreationRequest request){
        return service.createGroup(request);
    }

    @GetMapping(path = "/groups")
    public List<GroupInfoResponse> getGroupsInfo(){
        return service.getGroupsInfo();
    }

    @GetMapping(path = "/group/{id}")
    public GroupFullInfoResponse getFullGroupInfo(@PathVariable(name = "id") Integer id){
        return service.getFullGroupInfo(id);
    }

    @PutMapping(path = "/group/{id}")
    public void updateGroup(@PathVariable(name = "id") Integer id,
                            @Valid @RequestBody GroupCreationRequest request){
        service.updateGroup(id, request);
    }

    @DeleteMapping(path = "/group/{id}")
    public void updateGroup(@PathVariable(name = "id") Integer id){
        service.deleteGroup(id);
    }

    @PostMapping(path = "/group/{id}/participant")
    public Integer addMember(@PathVariable(name = "id") Integer id,
                             @Valid @RequestBody MemberCreationRequest request){
        return service.addMemberToGroup(id, request);
    }

    @DeleteMapping(path = "/group/{group_id}/participant/{participant_id}")
    public void deleteMember(@PathVariable(name = "group_id") Integer groupId,
                            @PathVariable(name = "participant_id") Integer memberId) throws IllegalAccessException {
        service.removeMemberFromGroup(groupId, memberId);
    }

    @PostMapping(path = "/group/{id}/toss")
    public List<ParentMemberInfoResponse> tossGroup(@PathVariable(name = "id") Integer id) throws IllegalAccessException {
        return service.tossGroup(id);
    }

    @GetMapping(path = "/group/{group_id}/participant/{participant_id}")
    public ChildMemberInfoResponse getRecipient(@PathVariable(name = "group_id") Integer groupId,
                                                @PathVariable(name = "participant_id") Integer memberId) throws IllegalAccessException {
        return service.getRecipient(groupId, memberId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class,  MethodArgumentNotValidException.class, IllegalAccessException.class})
    public String processException(Exception e){
        return e.getMessage();
    }
}
