package com.aavartix.engine_core.service;

import com.aavartix.engine_core.dto.CreateGroupRequest;
import com.aavartix.engine_core.model.ChitGroup;
import com.aavartix.engine_core.model.GroupMember;
import com.aavartix.engine_core.model.User;
import com.aavartix.engine_core.repository.ChitGroupRepository;
import com.aavartix.engine_core.repository.GroupMemberRepository;
import com.aavartix.engine_core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
public class ChitGroupService {

    private final ChitGroupRepository groupRepository;
    private final GroupMemberRepository memberRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChitGroupService(ChitGroupRepository groupRepository, GroupMemberRepository memberRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ChitGroup createGroup(CreateGroupRequest request) {
        ChitGroup group = new ChitGroup();
        group.setGroupName(request.getGroupName());
        group.setTotalPoolAmount(request.getTotalPoolAmount());
        group.setTotalMonths(request.getTotalMonths());
        group.setMaxDiscountAllowed(request.getMaxDiscountAllowed());

        // Automatically calculate what the monthly contribution should be
        BigDecimal monthlyContribution = request.getTotalPoolAmount()
                .divide(BigDecimal.valueOf(request.getTotalMonths()), 2, RoundingMode.HALF_UP);
        group.setMonthlyContribution(monthlyContribution);

        return groupRepository.save(group);
    }

    @Transactional
    public GroupMember joinGroup(UUID groupId, UUID userId) {
        ChitGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        if (memberRepository.existsByGroupAndUser(group, user)) {
            throw new IllegalArgumentException("User is already a member of this group!");
        }

        long currentMemberCount = memberRepository.countByGroup(group);
        if (currentMemberCount >= group.getTotalMonths()) {
            throw new IllegalArgumentException("This Chit Group is completely full!");
        }

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);

        // This is our OS Algorithm logic! The Round Robin pointer relies on this position.
        member.setRoundRobinPosition((int) currentMemberCount + 1);

        return memberRepository.save(member);
    }
}