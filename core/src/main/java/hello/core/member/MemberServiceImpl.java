package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
자동 의존 관계주입이 필요하다.
 */
@Component
public class MemberServiceImpl implements MemberService{
  private final MemberRepository memberRepository;

@Autowired //타입에 맞는 애를 찾아서 자동으로 연결해서 주입해준다. -> 자동의존주입 //근데 만약에 여러개 있으면 어쩢;?
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);  //오버라이드 된!!
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.finbyId(memberId);
  }
}
