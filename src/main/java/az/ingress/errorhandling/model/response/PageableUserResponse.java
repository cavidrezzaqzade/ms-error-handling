package az.ingress.errorhandling.model.response;

import az.ingress.errorhandling.model.UserDto;
import lombok.*;

import java.util.List;

/**
 * @author caci
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageableUserResponse {
    private List<UserDto> users;
    private int lastPageNumber;
    private long totalElements;
    private boolean hasNextPage;
}