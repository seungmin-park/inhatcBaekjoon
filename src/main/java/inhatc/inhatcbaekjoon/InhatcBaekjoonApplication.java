package inhatc.inhatcbaekjoon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;

@EnableScheduling
@SpringBootApplication
public class InhatcBaekjoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(InhatcBaekjoonApplication.class, args);
    }

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
