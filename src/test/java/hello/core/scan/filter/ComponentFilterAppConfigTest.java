package hello.core.scan.filter;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);

        Assertions.assertNotNull(beanA);


        Assertions.assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("beanB", BeanB.class));
    }


    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter
            (type = FilterType.ANNOTATION,
                    classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter
                    (type = FilterType.ANNOTATION,
                            classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{
        @Bean(name = "memoryMemberRepository")
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }
    }

}
