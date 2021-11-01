package inhatc.inhatcbaekjoon.repository;

import inhatc.inhatcbaekjoon.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findAllSortingByRating(){
        return em.createQuery("select m from Member m order by m.baekJoon.rating desc ",Member.class)
                .getResultList();
    }

    public Member findByEmail(String email){
        return em.createQuery("select m from Member m where m.email =: email",Member.class).setParameter("email",email).getSingleResult();
    }

}
