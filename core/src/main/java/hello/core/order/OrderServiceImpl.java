package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 "필수값"에 대한 생성자를 만들어준다.
public class OrderServiceImpl implements  OrderService{

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;


  @Autowired  // <- 생성자 하나로 생략가능‼️
  public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolich") DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }


  @Override
  public Order createOrder(Long memberID, String itemName, int itemPrice) {
    Member member = memberRepository.finbyId(memberID);
    int discountPrice = discountPolicy.discount(member,itemPrice);

    return new Order(memberID,itemName,itemPrice,discountPrice);
  }

  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
