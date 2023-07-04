package az.ingress.errorhandling.service;

import az.ingress.errorhandling.model.UserDetails;
import az.ingress.errorhandling.model.UserDto;
import az.ingress.errorhandling.model.criteria.PageCriteria;
import az.ingress.errorhandling.model.criteria.UserCriteria;
import az.ingress.errorhandling.model.response.PageableUserResponse;

import java.util.List;

/**
 * @author caci
 */

public interface UserService {

    List<UserDto> getAll();

    UserDto add(UserDto userDto);

    void delete(Long id);

    UserDto update(UserDto dto, Long id);

    UserDto update(UserDetails userDetails);

    PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria);
}
