package az.ingress.errorhandling.service.feign;

import az.ingress.errorhandling.model.client.CatFactDto;
import az.ingress.errorhandling.model.client.InformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

public interface FeignCall {

    CatFactDto getCatFact();

    String fileManagement(MultipartFile file, InformationDto info);

    String getInfo(String info);
}
