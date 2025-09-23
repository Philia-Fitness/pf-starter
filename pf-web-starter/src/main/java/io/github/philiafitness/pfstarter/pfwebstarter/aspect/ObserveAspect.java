package io.github.philiafitness.pfstarter.pfwebstarter.aspect;

import io.github.philiafitness.pfstarter.pfwebstarter.bean.response.BaseResponse;
import io.github.philiafitness.pfstarter.pfwebstarter.enums.ResponseCodesEnum;
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

    @Pointcut("@annotation(io.github.philiafitness.pfstarter.pfwebstarter.aspect.Observe)")
    public void annotationPoint() {
    }

    @Around("annotationPoint()")
    public Object observeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            return BaseResponse.builder()
                    .responseCode(ResponseCodesEnum.GENERIC_ERROR.getErrorCode())
                    .responseMessage(ResponseCodesEnum.GENERIC_ERROR.getDescription())
                    .status(ResponseCodesEnum.GENERIC_ERROR.getStatus())
                    .build();
        }
    }
}
