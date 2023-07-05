package az.ingress.errorhandling.controller;

import az.ingress.errorhandling.client.FileManagementClient;
import az.ingress.errorhandling.model.client.CatFactDto;
import az.ingress.errorhandling.model.client.InformationDto;
import az.ingress.errorhandling.service.feign.FeignCall;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caci
 */

@RestController
@RequestMapping("/v1/feign")
@RequiredArgsConstructor
@Tag(name = "Feign", description = "the File Management api")
public class FeignController {

    private final FeignCall call;
    private final FileManagementClient fileManagementClient;

    @GetMapping
    @Operation(summary = "get cat fact", description = "get cat fact", tags = {"Feign"})
    public CatFactDto getAll(){
        return call.getCatFact();
    }

    @PostMapping(/*consumes = MediaType.MULTIPART_FORM_DATA_VALUE*//*consumes = {"multipart/form-data", "application/json"}*/)
    @Operation(summary = "upload images", description = "upload images", tags = {"Feign"})
    public ResponseEntity<?> uploadImage(@RequestPart("files") MultipartFile[] files, @RequestPart("info") InformationDto info){
        System.out.println(files.length + " : " + info.getInformation());
        return fileManagementClient.uploadImage(files, info);
    }

    @GetMapping("/{info}")
    @Operation(summary = "get information", description = "get information", tags = {"Feign"})
    @ResponseStatus(HttpStatus.OK)
    public String getInfo(@PathVariable(name = "info") String info){
        return fileManagementClient.getInfo(info);
    }

}
