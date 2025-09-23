package io.github.philiafitness.pfstarter.pfwebstarter.aspect;

import it.sisal.digital.npplstarter.npplwebstarter.bean.response.NpplBaseResponse;
import it.sisal.digital.npplstarter.npplwebstarter.enums.ResponseCodesEnum;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ObserveAspect {
    @PostConstruct
    void logInit() {
        log.info("Init {}", this.getClass().getSimpleName());
    }

    @Pointcut("@annotation(it.sisal.digital.npplstarter.npplwebstarter.aspect.Observe)")
    public void annotationPoint() {
    }

    @Around("annotationPoint()")
    public Object observeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            return NpplBaseResponse.builder()
                    .responseCode(ResponseCodesEnum.GENERIC_ERROR.getNpplErrorCode())
                    .responseMessage(ResponseCodesEnum.GENERIC_ERROR.getDescription())
                    .status(ResponseCodesEnum.GENERIC_ERROR.getStatus())
                    .build();
        }
    }
}
