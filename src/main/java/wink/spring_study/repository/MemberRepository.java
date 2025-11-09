package wink.spring_study.repository;
import wink.spring_study.domain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원 반환
    Optional<Member> findById(Long id); //아이디로 회원 찾기, optional: 없으면 null로 반환
    Optional<Member> findByName(String name);
    List<Member> findAll(); //저장된 모든 회원 리스트 반환
}

