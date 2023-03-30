package com.example.yaprofi.service;

import com.example.yaprofi.dto.*;
import com.example.yaprofi.entity.GroupEntity;
import com.example.yaprofi.entity.MemberEntity;
import com.example.yaprofi.repository.GroupRepo;
import com.example.yaprofi.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AppService {
    @Autowired
    GroupRepo groupRepo;

    @Autowired
    MemberRepo memberRepo;
    public Integer createGroup(GroupCreationRequest request) {
        var group = new GroupEntity();
        group.setName(request.getName());
        group.setDescription(request.getDescription());
        return groupRepo.save(group).getId();
    }

    public List<GroupInfoResponse> getGroupsInfo() {
        return groupRepo.findAll().stream().map(GroupInfoResponse::new)
                .collect(Collectors.toList());
    }

    public GroupFullInfoResponse getFullGroupInfo(Integer id) {
        var group = groupRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", id)));
        return new GroupFullInfoResponse(group);
    }

    public void updateGroup(Integer id, GroupCreationRequest request) {
        var group = groupRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", id)));
        group.setName(request.getName());
        group.setDescription(request.getDescription());
        groupRepo.save(group);
    }

    public void deleteGroup(Integer id) {
        var group = groupRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", id)));
        groupRepo.delete(group);
    }

    public Integer addMemberToGroup(Integer groupId, MemberCreationRequest request) {
        var group = groupRepo.findById(groupId).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", groupId)));
        var newMember = new MemberEntity();
        newMember.setName(request.getName());
        newMember.setWish(request.getWish());
        group.addMember(newMember);
        return groupRepo.save(group).getMembers().get(group.getMembers().size()-1).getId();
    }

    public void removeMemberFromGroup(Integer groupId, Integer memberId) throws IllegalAccessException {
        var group = groupRepo.findById(groupId).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", groupId)));
        var member = memberRepo.findById(memberId).orElseThrow(() ->
                new NoSuchElementException(String.format("нет участника с номером %d", memberId)));
        if(member.getGroup() == null || !member.getGroup().equals(group)){
            throw new IllegalAccessException(String.format("Участник %d не состоит в группе %d", memberId, groupId));
        }
        member.deleteGroup();
        memberRepo.save(member);
    }

    public List<ParentMemberInfoResponse> tossGroup(Integer groupId) throws IllegalAccessException {
        var group = groupRepo.findById(groupId).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", groupId)));
        int size = group.getMembers().size();
        if(size >= 3){
            throw new IllegalAccessException(String.format("В группе %d недостаточно участников для жеребьевки", groupId));
        }
        //TODO сделать настоящий рандом
        for(int i = 0; i < size; i++){
            group.getMembers().get(i).setRecipient(group.getMembers().get((i+1)%size));
        }
        return group.getMembers().stream().map(ParentMemberInfoResponse::new).collect(Collectors.toList());
    }

    public ChildMemberInfoResponse getRecipient(Integer groupId, Integer memberId) throws IllegalAccessException {
        var group = groupRepo.findById(groupId).orElseThrow(() ->
                new NoSuchElementException(String.format("нет группы с номером %d", groupId)));
        var member = memberRepo.findById(memberId).orElseThrow(() ->
                new NoSuchElementException(String.format("нет участника с номером %d", memberId)));
        if(member.getRecipient() == null){
            throw new IllegalAccessException(String.format("У участника %d не назначен получатель подарка", memberId));
        }
        return new ChildMemberInfoResponse(member.getRecipient());
    }
}
