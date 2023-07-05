package az.ingress.errorhandling.service;

import az.ingress.errorhandling.dao.entity.User;
import az.ingress.errorhandling.dao.repository.UserRepository;
import az.ingress.errorhandling.exception.ApplicationException;
import az.ingress.errorhandling.exception.Errors;
import az.ingress.errorhandling.mapper.PageableMapper;
import az.ingress.errorhandling.mapper.UserMapper;
import az.ingress.errorhandling.model.UserDetails;
import az.ingress.errorhandling.model.UserDto;
import az.ingress.errorhandling.model.criteria.PageCriteria;
import az.ingress.errorhandling.model.criteria.UserCriteria;
import az.ingress.errorhandling.model.response.PageableUserResponse;
import az.ingress.errorhandling.service.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author caci
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PageableMapper pageableMapper;

    private static final Integer PAGE_DEFAULT = 1;
    private static final Integer COUNT_DEFAULT = 10;

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return mapper.entitiesToDtos(users);
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = mapper.dtoToEntity(userDto);
        return mapper.entityToDto(repository.save(user));
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(Errors.DATA_NOT_FOUND, Map.of("id", id)));
        repository.delete(user);
    }

    @Override
    public UserDto update(UserDto dto, Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(Errors.DATA_NOT_FOUND, Map.of("id", id)));
        User userNew = mapper.updateUser(user, dto);
        return mapper.entityToDto(repository.save(userNew));
    }

    @Override
    public UserDto update(UserDetails userDetails) {
        User user = repository.findById(userDetails.getId()).orElseThrow(() -> new ApplicationException(Errors.DATA_NOT_FOUND, Map.of("id", userDetails.getId())));

        user.setAge(userDetails.getAge());
        user.setName(userDetails.getName());

        return mapper.entityToDto(repository.save(user));
    }

    @Override
    public PageableUserResponse getUsers(PageCriteria pageCriteria, UserCriteria userCriteria) {

        var pageNumber = pageCriteria.getPage() == null ? PAGE_DEFAULT : pageCriteria.getPage();
        var count = pageCriteria.getCount() == null ? COUNT_DEFAULT : pageCriteria.getCount();

        Page<User> usersPage = repository.findAll(new UserSpecification(userCriteria), PageRequest.of(pageNumber, count));
        return pageableMapper.mapToPageableResponse(usersPage);
    }
}
