package inhatc.inhatcbaekjoon.service;

import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        return memberRepository.save(member);
    }

    public Member findById(Long id){
        return memberRepository.findById(id);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public List<Member> findAllSortByRating(){
        return memberRepository.findAllSortingByRating();
    }

    @Transactional
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email);
    }
}
