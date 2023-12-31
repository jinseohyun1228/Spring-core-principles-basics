package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements  OrderService{
 //주문을 받은 다음, 회원 조회 , 할인 적용을 해야한다.
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  //테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }


  @Override
  public Order createOrder(Long memberID, String itemName, int itemPrice) {
    Member member = memberRepository.finbyId(memberID);
    int discountPrice = discountPolicy.discount(member,itemPrice);

    return new Order(memberID,itemName,itemPrice,discountPrice);
  }
}
