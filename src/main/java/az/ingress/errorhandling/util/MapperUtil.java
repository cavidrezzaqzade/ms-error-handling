package az.ingress.errorhandling.util;

import java.io.InputStream;
import static az.ingress.errorhandling.mapper.ObjectMapperFactory.OBJECT_MAPPER_FACTORY;

/**
 * @author caci
 */

public enum MapperUtil {
    MAPPER_UTIL;

    public <T> T map(InputStream source, Class<T> clazz){
        try {
            return OBJECT_MAPPER_FACTORY.createObjectMapperInstance().readValue(source, clazz);
        } catch (Exception ex){
            throw new IllegalStateException(ex.getMessage());
        }

    }
}
