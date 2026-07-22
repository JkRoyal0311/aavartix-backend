package com.aavartix.engine_core.controller;

import com.aavartix.engine_core.dto.CreateGroupRequest;
import com.aavartix.engine_core.dto.JoinGroupRequest;
import com.aavartix.engine_core.model.ChitGroup;
import com.aavartix.engine_core.model.GroupMember;
import com.aavartix.engine_core.service.ChitGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
public class ChitGroupController {

    private final ChitGroupService groupService;

    @Autowired
    public ChitGroupController(ChitGroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody CreateGroupRequest request) {
        try {
            ChitGroup newGroup = groupService.createGroup(request);
            return ResponseEntity.ok("Group created! Monthly contribution set to Rs. "
                    + newGroup.getMonthlyContribution() + ". Group ID: " + newGroup.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{groupId}/join")
    public ResponseEntity<String> joinGroup(@PathVariable UUID groupId, @RequestBody JoinGroupRequest request) {
        try {
            GroupMember member = groupService.joinGroup(groupId, request.getUserId());
            return ResponseEntity.ok("Successfully joined! Your Round Robin position is: " + member.getRoundRobinPosition());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}