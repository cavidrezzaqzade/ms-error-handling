package az.ingress.errorhandling.client.decoder;

import az.ingress.errorhandling.exception.Errors;
import az.ingress.errorhandling.exception.FileFeignClientException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Map;

import static az.ingress.errorhandling.util.MapperUtil.MAPPER_UTIL;
import static az.ingress.errorhandling.client.decoder.JsonNodeFieldName.CODE;

/**
 * @author caci
 */

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    //TODO: CLIENT_ERROR
    @Override
    public Exception decode(String methodKey, Response response) {

        String errorMessage = Errors.CLIENT_ERROR.getMessage();
        int status = response.status();

        JsonNode jsonNode;
        try(InputStream body = response.body().asInputStream()){
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch(Exception e){
            throw new FileFeignClientException(Errors.CLIENT_ERROR, Map.of("message", errorMessage));
        }

        if(jsonNode.has(CODE.getValue()))
            errorMessage = jsonNode.get(CODE.getValue()).asText();

        log.error("message: {}, Method: {}", errorMessage, methodKey);

        return new FileFeignClientException(Errors.CLIENT_ERROR, Map.of("message", errorMessage));
    }
}
