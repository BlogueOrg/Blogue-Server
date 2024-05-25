package com.blogue.blogue.member.controller;

import com.blogue.blogue.member.domain.Member;
import com.blogue.blogue.member.dto.MemberDto;
import com.blogue.blogue.member.dto.request.CreateMemberRequest;
import com.blogue.blogue.member.dto.response.CreateMemberResponse;
import com.blogue.blogue.member.service.MemberService;
import com.blogue.blogue.util.ListResultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest request) {
        // API 개발할 땐 엔티티를 파라미터로 받지 말기.
        // 엔티티 함부로 노출시키지 말기.

        Member member = new Member();
        member.setUsername(request.getUsername());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @GetMapping("/")
    public ListResultResponse getMembers(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(u -> new MemberDto(u.getUsername()))
                        .collect(Collectors.toList());
        return new ListResultResponse(collect.size(), collect); // 오브젝트 타입으로 반환
    }
}