package az.ingress.errorhandling.model.client;

import lombok.*;

/**
 * @author caci
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatFactDto {

    private String fact;
    private Integer length;

}
