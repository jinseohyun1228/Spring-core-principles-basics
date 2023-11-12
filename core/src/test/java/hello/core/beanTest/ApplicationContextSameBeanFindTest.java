package hello.core.beanTest;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBanConfig.class);

  @Test
  @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복오류가 발생한다.")
  void findBeanByTypeDuplicate() {
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
            ()-> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다..")
  void findBeanByName() {
    MemberRepository bean = ac.getBean("memberRepository1",MemberRepository.class);
    assertThat(bean).isInstanceOf(MemberRepository.class);
  }

  @Test
  @DisplayName("특정타입을 모두 조회하기")
  void findAllBeanByType() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    for (String key : beansOfType.keySet()) {
      System.out.println("key  = " + key + "value = "+ beansOfType.get(key));
    }
    System.out.println("beansOfType = " + beansOfType);
    assertThat(beansOfType.size()).isEqualTo(2);
  }

  //타입이 동일한 두개의 빈 등록
  @Configuration
  static class SameBanConfig {
    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
