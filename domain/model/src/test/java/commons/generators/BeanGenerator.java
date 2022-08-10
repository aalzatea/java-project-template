package commons.generators;

import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.lang.reflect.Type;

public final class BeanGenerator {

    private BeanGenerator() {
    }

    public static <T> T generateBean(Class<T> clazz) {
        return generateBeanWithGenericTypes(clazz, (Type) null);
    }

    public static <T> T generateBeanWithGenericTypes(Class<T> clazz, Type... types) {
        var podamFactory = new PodamFactoryImpl();
        return podamFactory.manufacturePojoWithFullData(clazz, types);
    }
}
