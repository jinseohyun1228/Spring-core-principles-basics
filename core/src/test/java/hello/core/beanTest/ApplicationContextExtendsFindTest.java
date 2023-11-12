package hello.core.beanTest;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
  void findBeanByParentTypeDuplicate() {
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () ->
            ac.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 이름을 지정하면 된다.")
  void findBeanByParentTypeBeanName() {
    DiscountPolicy rateDiscountPolich = ac.getBean("rateDiscountPolich", DiscountPolicy.class);
    //실제 구현 객체는 rete~
    assertThat(rateDiscountPolich).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면, 특정 하위 타입으로 조회하면 된다.")
  void findBeanByType() {
    DiscountPolicy rateDiscountPolich = ac.getBean(RateDiscountPolicy.class);
    assertThat(rateDiscountPolich).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회하기")
  void findAllBeanByParentType() {
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    assertThat(beansOfType.size()).isEqualTo(2);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + "value = " + beansOfType.get(key));
    }
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회하기 : Object")
  void findAllBeanByObjectType() {
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + "value = " + beansOfType.get(key));
    }
  }



  @Configuration
  static class TestConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }
    @Bean //굳이 인터페이스로 하는 이유 : 역할을 보기 위해서
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }
}
