package hello.core.member;

public class MemberServiceImpl implements MemberService{
  private final MemberRepository memberRepository;
  //테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

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
