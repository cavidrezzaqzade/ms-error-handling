package az.ingress.errorhandling.service.feign;

import az.ingress.errorhandling.client.CatFactClient;
import az.ingress.errorhandling.client.FileManagementClient;
import az.ingress.errorhandling.model.client.CatFactDto;
import az.ingress.errorhandling.model.client.InformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

@Service
@RequiredArgsConstructor
public class FeignCallImpl implements FeignCall {

    private final CatFactClient catClient;
    private final FileManagementClient fileClient;

    @Override
    public CatFactDto getCatFact() {
        return catClient.getCatFact();
    }

    @Override
    public ResponseEntity<?> fileManagement(MultipartFile[] files, InformationDto info) {
        return fileClient.uploadImage(files, info);
    }

    @Override
    public String getInfo(String info) {
        return fileClient.getInfo(info);
    }
}
