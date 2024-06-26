package com.blogue.blogue.member.service;

import com.blogue.blogue.member.domain.Member;
import com.blogue.blogue.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional // 읽기 전용인 기능이 많으니까 전체를 readOnly = true로 해두고 이 기능만 false로
    // 영속성 컨텍스트 만들어지고 데이터베이스 커넥션 가져오고
    // OSIV OFF라면 로직이 끝난 후 데이터 베이스 커넥션 플러시 커밋하고 영속성 컨텍스트를 없애고 데이터 베이스 커넥션 반환
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUsername(member.getUsername()); // 더 안전하게 하려면 unique 제약 조건 걸어주는 게 더 좋다.
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void updateUsername(Long id, String username) {
        Member member = memberRepository.findById(id).get();
        member.setUsername(username);
        // member를 반환할 수도 있지만 그렇게 되면 커맨드와 쿼리를 한 메소드 안에서 하는 게 된다.
        // id 정도만 반환하거나 아예 반환하지 않는 게 깔끔하다.
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        member.setIsDeleted(true);
    }
}