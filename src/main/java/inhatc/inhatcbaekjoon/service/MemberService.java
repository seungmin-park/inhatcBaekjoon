package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member join(Member member){
        return memberRepository.save(member);
    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public List<Member> findAllByRatingDesc(){
        return memberRepository.findAllByRatingDesc();
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
