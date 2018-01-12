package com.mkejug.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
public class PerfBPP implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();

        final boolean[] proxyCandidate = {false};
        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                PerfMonitor annotation = AnnotationUtils.getAnnotation(method, PerfMonitor.class);
                if (annotation != null) {
                    proxyCandidate[0] = true;
                }
            }
        });

        if (proxyCandidate[0]) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    PerfMonitor annotation = AnnotationUtils.findAnnotation(method, PerfMonitor.class);
                    if (annotation == null) {
                        return methodProxy.invoke(o, args);
                    } else {
                        long start = System.currentTimeMillis();
                        try {
                            return methodProxy.invokeSuper(o, args);
                        } finally {
                            System.out.println(
                                    String.format("**********  %s took %sms to invoke",
                                            method.getName(),
                                            System.currentTimeMillis() - start));
                        }
                    }
                }
            });
            return enhancer.create();
        } else {
            return bean;
        }

    }
}
