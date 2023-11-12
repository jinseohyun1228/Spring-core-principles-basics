package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
  public static void main(String[] args) {
//    MemberService memberService = new MemberServiceImpl(memberRepository);
//    OrderService orderService = new OrderServiceImpl(memberRepository, discountPolicy);

//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();
//    OrderService orderService = appConfig.orderService();

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService",MemberService.class);
    OrderService orderService = ac.getBean("orderService",OrderService.class);

    Long memberID = 1L;
    Member memberA = new Member(memberID, "memberA", Grade.VIP);
    memberService.join(memberA);

    Order order = orderService.createOrder(memberID, "itemA", 20000);

    System.out.println("order = " + order);

  }
}
