package hello.core.beanTest;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextBasicFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("이름 없이 타입으로만 조회")
  void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class);
    System.out.println("memberService.getClass() = " + memberService.getClass());
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체타입으로 조회")
  void findBeanByName2() {
    MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
  /*
    @Bean
  public MemberService memberService() { //key : 메소드 이름(membserService)
    return new MemberServiceImpl(memberRepository()); //참조값을 넣어준다.
  } 실제 객체로 등록하기 때문에 구체적인 등록으로도 만들 수 있다.
   */

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByNameX() {
//    MemberService memberService = ac.getBean("dedede", MemberServiceImpl.class);
    Assertions.assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("dedede", MemberServiceImpl.class));
  }
}
