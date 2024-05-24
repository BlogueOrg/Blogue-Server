package com.blogue.blogue.member.controller;

import com.blogue.blogue.member.domain.Member;
import com.blogue.blogue.member.dto.request.CreateMemberRequest;
import com.blogue.blogue.member.dto.response.CreateMemberResponse;
import com.blogue.blogue.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}