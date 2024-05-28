package com.blogue.blogue.member.controller;

import com.blogue.blogue.member.domain.Member;
import com.blogue.blogue.member.dto.MemberDto;
import com.blogue.blogue.member.dto.request.CreateMemberRequest;
import com.blogue.blogue.member.dto.request.UpdateMemberUsernameRequest;
import com.blogue.blogue.member.dto.response.CreateMemberResponse;
import com.blogue.blogue.member.dto.response.DeleteMemberResponse;
import com.blogue.blogue.member.dto.response.GetMemberResponse;
import com.blogue.blogue.member.dto.response.UpdateMemberUsernameResponse;
import com.blogue.blogue.member.service.MemberService;
import com.blogue.blogue.util.ListResultResponse;
import com.blogue.blogue.util.ResponseDTO;
import com.blogue.blogue.util.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity createMember(@RequestBody @Valid CreateMemberRequest request) {
        // API 개발할 땐 엔티티를 파라미터로 받지 말기.
        // 엔티티 함부로 노출시키지 말기.

        Member member = new Member();
        member.setUsername(request.getUsername());

        Long id = memberService.join(member);
        ResponseDTO response = new ResponseDTO(Status.MEMBER_CREATED, new CreateMemberResponse(id));
        return response.returnResponseEntity();
    }

    @GetMapping("/")
    public ResponseEntity getMembers(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(u -> new MemberDto(u.getUsername()))
                        .collect(Collectors.toList());

        ResponseDTO response = new ResponseDTO(
                Status.MEMBERS_FETCHED,
                new ListResultResponse(collect.size(), collect)
        );
        return response.returnResponseEntity();
    }

    @GetMapping("/{memberId}")
    public GetMemberResponse getMember(@PathVariable Long memberId) {
        Member findMember = memberService.findMember(memberId);
        return new GetMemberResponse(findMember.getUsername());
    }

    @PutMapping("/{memberId}")
    public UpdateMemberUsernameResponse updateMemberUsername(@PathVariable Long memberId,
                                                             @RequestBody @Valid UpdateMemberUsernameRequest request){
        // 커맨드와 쿼리 분리 -  유지보수성 증대
        memberService.updateUsername(memberId, request.getUsername()); // 커맨드
        Member findMember = memberService.findMember(memberId); // 쿼리
        return new UpdateMemberUsernameResponse(findMember.getId(), findMember.getUsername());
    }

    @DeleteMapping("/{memberId}")
    public DeleteMemberResponse deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId); // 커맨드
        return new DeleteMemberResponse("Deleted");
    }
}