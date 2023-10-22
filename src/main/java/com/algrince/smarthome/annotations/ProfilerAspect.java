package com.algrince.smarthome.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProfilerAspect {

    @Around("@annotation(profiler)")
    public Object wrap(ProceedingJoinPoint pjp, Profiler profiler) throws Throwable {
        long beforeMilliseconds = System.currentTimeMillis();
        Object result = pjp.proceed(pjp.getArgs());
        long postMilliseconds = System.currentTimeMillis();
        System.out.println("Took time: %d".formatted(postMilliseconds - beforeMilliseconds));
        return result;
    }
}
