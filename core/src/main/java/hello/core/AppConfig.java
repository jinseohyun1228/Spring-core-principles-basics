package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정정보
public class AppConfig {

  /*
     @Bean memberService -> memberRepository()  -> new MemoryMemberRepository()
     @Bean orderService() -> memberRepository() -> new MemoryMemberRepository()
                           discountPolicy()  -> new RateDiscountPolicy();
                 -------> new MemoryMemberRepository()가 두 번!! 과연 싱글톤 패턴일까?
   */


  @Bean
  public MemberService memberService() { //key : 메소드 이름(membserService)
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository()); //참조값을 넣어준다.
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl( memberRepository(), discountPolicy());
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy(); //OCP 원칙 지킴!!
  }


}
