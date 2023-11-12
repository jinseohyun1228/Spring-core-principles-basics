package hello.core;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan( //컴포넌트를 사용하기 위해서~!! 대신 @Configuration가 붙은 클래스는 사용하지 않도록! @_@
        excludeFilters =  @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes =  Configuration.class)
)
public class AutoAppConfig {

//  @Bean(name = "memoryMemberRepository")
//  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//  }

}
