package inhatc.inhatcbaekjoon.config.auth;

import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //리턴시 security session <= Authentication <= UserDetails
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            return new PrincipalDetails(member);
        }
        return null;
    }
}
