package az.ingress.errorhandling.client;

import az.ingress.errorhandling.client.decoder.CustomErrorDecoder;
import az.ingress.errorhandling.config.FeignSupportConfig;
import az.ingress.errorhandling.model.client.InformationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

@FeignClient(name = "file-management-client", url = "${file-management.client.url.root}", configuration = {FeignSupportConfig.class, CustomErrorDecoder.class})
@Configuration
public interface FileManagementClient {

    @PostMapping(value = "/v1/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadImage(@RequestPart("files") MultipartFile[] files, @RequestPart("info") InformationDto info);

    @GetMapping(value = "/v1/files/{info}")
    String getInfo(@PathVariable("info") String info);
}
