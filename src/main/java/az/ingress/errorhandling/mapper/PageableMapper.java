package az.ingress.errorhandling.mapper;

import az.ingress.errorhandling.dao.entity.User;
import az.ingress.errorhandling.model.response.PageableUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

/**
 * @author caci
 */

//@Mapper(componentModel = "spring")
@Configuration
@AllArgsConstructor
public class PageableMapper {

    private final UserMapper userMapper;

    public PageableUserResponse mapToPageableResponse(Page<User> usersPage){
        return PageableUserResponse.builder()
                .users(usersPage.getContent().stream().map(userMapper::entityToDto).collect(Collectors.toList()))
                .totalElements(usersPage.getTotalElements())
                .hasNextPage(usersPage.hasNext())
                .lastPageNumber(usersPage.getTotalPages())
                .build();
    }

}
