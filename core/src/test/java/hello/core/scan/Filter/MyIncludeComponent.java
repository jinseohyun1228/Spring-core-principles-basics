package hello.core.scan.Filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)  //클래스 래벨
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
