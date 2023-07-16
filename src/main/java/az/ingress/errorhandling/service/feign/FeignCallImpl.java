package az.ingress.errorhandling.service.feign;

import az.ingress.errorhandling.client.CatFactClient;
import az.ingress.errorhandling.client.FileManagementClient;
import az.ingress.errorhandling.exception.Errors;
import az.ingress.errorhandling.exception.FileFeignClientException;
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
    public String fileManagement(MultipartFile file, InformationDto info) {
        return fileClient.uploadImage(file, info);
    }

    @Override
    public String getInfo(String info) {

//        String data;
//
//        try {
//            data = fileClient.getInfo(info);
//        } catch (FileFeignClientException ex){
//            throw new FileFeignClientException(Errors.CLIENT_ERROR, 5);
//        }

        return fileClient.getInfo(info);
    }
}
