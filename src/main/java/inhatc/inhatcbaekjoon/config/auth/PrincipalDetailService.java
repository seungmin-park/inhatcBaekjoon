package inhatc.inhatcbaekjoon.config.auth;

import inhatc.inhatcbaekjoon.domain.Member;
import inhatc.inhatcbaekjoon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //리턴시 security session <= Authentication <= UserDetails
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = userRepository.findByEmail(email);
        if (member != null) {
            return new PrincipalDetails(member);
        }
        return null;
    }
}
