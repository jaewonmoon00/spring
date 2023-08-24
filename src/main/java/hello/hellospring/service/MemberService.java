package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service
public class MemberService {
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    // command + R to create a constructor
    //@Autowired // telling spring that Repository is needed.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * / + * + * + Enter = 자동완성
     * 회원가입
     */
    public Long join(Member member){
        // check if name is unique
        // command + option + v = declare data type and create variable name
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

