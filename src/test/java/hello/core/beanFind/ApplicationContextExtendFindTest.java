package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolice;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendFindTest {

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(TestConfig.class);

    
    @Test
    void findBeanByParentTypeDuplicate(){
        //given
        //when
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        //then
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        //given
        //when
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        //then
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanByNameSubType(){

        //given

        //when

        //then
    }

    @Configuration
    static class TestConfig{
        
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fidDiscountPolicy(){
            return new FixDiscountPolice();
        }
    }
}
