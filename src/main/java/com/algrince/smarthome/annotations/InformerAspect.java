package com.algrince.smarthome.annotations;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InformerAspect {

    @Around("@annotation(informer)")
    public Object wrap(ProceedingJoinPoint pjp, Informer informer) throws Throwable {

        Signature signature = pjp.getSignature();
        String methodName = signature.getName();
        String declaringTypeName = signature.getDeclaringTypeName();

        Object[] args = pjp.getArgs();
        Object id;
        Object updatedObject;

        try {
            id = args[0];
            updatedObject = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Update method annotation used in %s method in %s"
                    .formatted(methodName, declaringTypeName));
        }

        System.out.println("Method %s invoked in %s".formatted(methodName, declaringTypeName));
        System.out.println("Object updated with id: %d".formatted(id));
        System.out.println("Requested update: " + updatedObject.toString());

        Object result = pjp.proceed();
        System.out.println("Update result is: " + result.toString());

        return result;
    }
}
