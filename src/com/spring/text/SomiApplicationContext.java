package com.spring.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SomiApplicationContext {
    private Map<String, Object> beanContainer = new HashMap<>();

//    public <T> T getBean(String name, Class<T> type) {
//
//    }

    // Spring Application start
    // new ApplicationContext();
    // context.start();

    // @ComponentScan(
    // package 기준으로 컴포넌트를 스캔 ->
    // com.spring.text.* @Component -> @Configuration

    // XML file <bean id="ddd" class="com.spring.text.TextConverterImpl />
    // Annotation based
    // @Component Scan
    void scanComponents() throws Exception {
        Class<?> config = SomiConfiguration.class;

        // getMethods
        Method[] methods = config.getDeclaredMethods();

        Object configInstance = config.getConstructor().newInstance();

        for(Method method: methods) {
            System.out.println(method.getName());

            // @Bean이 붙어있는지 여부
            if(method.isAnnotationPresent(Bean.class)) {
                System.out.println("붙어 있음");

                // 붙어있다면 -> beanContainer에 해당 메서드를 실행해서 리턴 받은 객체를 저장.
                // 이 때 키는 method 이름
                Object returnValue = method.invoke(configInstance);
                beanContainer.put(method.getName(), returnValue);
            } else {
                System.out.println("붙어있지 않음");
            }
        }
    }

    public void injectDependency() throws IllegalAccessException {
        TextConverterService service = new TextConverterService();

        beanContainer.put("textConverterService", service);

        Class<?> serviceType = TextConverterService.class;

        Field[] fields = serviceType.getDeclaredFields();
        for(Field field: fields) {
            if(field.isAnnotationPresent(Autowired.class)) {
                System.out.println("Autowired 필드 " + field.getName());

                Qualifier qualifier = field.getAnnotation(Qualifier.class);
                Object bean = beanContainer.get(qualifier.value());

                System.out.println(qualifier.value());
                System.out.println(bean);

                // bean null? not null
                field.setAccessible(true);
                field.set(service, bean);

                field.setAccessible(false);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SomiApplicationContext context = new SomiApplicationContext();
        context.scanComponents();
        context.injectDependency();

        System.out.println(context.beanContainer);

        TextConverterService service = (TextConverterService) context.beanContainer.get("textConverterService");
        System.out.println(service.convert("HELLO world"));

        new Thread(() -> {
            for(int i = 0 ; i < 1000 ; i++) {
                service.convert("");
            }
        }).start();

        new Thread(() -> {
            for(int i = 0 ; i < 1000 ; i++) {
                service.convert("");
            }
        }).start();

        System.out.println("Count: " + service.convertCount);
    }
}

@interface Component {
    String value2();
}

@Configuration
class ApplicationConfg {
    @Bean
    public TextConverter upperTextConverter() {
        return new TextConverterImpl();
    }
}

@Component(value2 = "dd")
@interface Configuration {

}

@Component(value2 = "dd")
@interface Service {

}
