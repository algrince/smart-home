package com.algrince.smarthome.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PIDInjectAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> beans = new HashMap<>();
    private Map<String, Object> objects = new HashMap<>();

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

            var fields = bean.getClass().getDeclaredFields();

            for (var field : fields) {
                if (field.getAnnotation(PIDInject.class) != null) {
                    beans.put(beanName, bean.getClass());
                    objects.put(beanName, bean);
                }
            }
            return bean;

    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {

            var clazz = beans.get(beanName);
            var object = objects.get(beanName);

            if (clazz != null) {
                var fields = clazz.getDeclaredFields();

                for (var field : fields) {
                    if (field.getAnnotation(PIDInject.class) != null) {
                        field.setAccessible(true);
                        field.set(object, ProcessHandle.current().pid());
                    }
                }
            }

            return bean;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
