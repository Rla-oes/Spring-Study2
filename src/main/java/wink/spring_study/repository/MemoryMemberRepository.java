package wink.spring_study.repository;
import wink.spring_study.domain.Member;
import  java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    // 실무에서는 동시성 문제가 있을 수 있어 공유되는 변수일때는 concurrent hashmap을 사용해야함
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //실무에서는 long 말고 atomic long
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버에 id 값 세팅
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //getname이 파라미터 name이랑 같은지 확인
                .findAny(); //루프 돌면서 하나 찾으면 걔 반환. 없으면 null
    }
    public void clearStore() {
        store.clear();
    }
}