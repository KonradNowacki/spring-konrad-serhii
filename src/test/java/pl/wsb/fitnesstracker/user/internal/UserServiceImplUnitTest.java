package pl.wsb.fitnesstracker.user.internal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_shouldReturnCreatedUser() {
        // given
        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@g.c"
        );

        when(userRepository.save(exampleUser)).thenReturn(exampleUser);

        // when
        final User result = userService.createUser(exampleUser);

        // then
        Assertions.assertEquals(exampleUser, result);
        Assertions.assertInstanceOf(User.class, result);
    }

    @Test
    public void getUser_shouldReturnUser() {
        // given
        final Long USER_ID = 9L;

        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@g.c"
        );

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(exampleUser));

        // when
        final Optional<User> result = userService.getUser(USER_ID);

        // then
        Assertions.assertEquals(exampleUser, result.get());
    }

    @Test
    public void getUserByEmail_shouldReturnUser() {
        // given
        final String EMAIL = "e@xample.com";

        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), EMAIL
        );

        when(userRepository.findByEmailIgnoreCase(EMAIL)).thenReturn(Optional.of(exampleUser));

        // when
        final Optional<User> result = userService.getUserByEmail(EMAIL);

        // then
        Assertions.assertEquals(exampleUser, result.get());
    }

    @Test
    public void findAllUsers_shouldReturnUser() {
        // given
        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@a.c"
        );

        final User exampleUser2 = new User(
                "fn2", "ln2", LocalDate.of(2002, 12, 10), "e12@a.c"
        );

        var users = List.of(exampleUser, exampleUser2);

        when(userRepository.findAll()).thenReturn(users);

        // when
        final List<User> result = userService.findAllUsers();

        // then
        Assertions.assertEquals(users, result);
    }

    @Test
    public void updateUser_shouldReturnUpdatedUser() {
        // given
        final Long USER_ID = 9L;

        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@a.c"
        );

        final User updatedExampleUser = new User(
                "fnEDITED", "lnEDITED", LocalDate.of(2000, 10, 10), "eEDITED@a.c"
        );

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(exampleUser));
        when(userRepository.save(exampleUser)).thenReturn(updatedExampleUser);

        // when
        final User result = userService.updateUser(USER_ID, exampleUser);

        // then
        Assertions.assertEquals(updatedExampleUser, result);
    }

    @Test
    public void deleteUser_shouldReturnDeletedUser() {
        // given
        final Long USER_ID = 9L;

        // when
        userService.deleteUserById(USER_ID);

        // then
        verify(userRepository).deleteById(USER_ID);
    }

    @Test
    public void getUsersOlderThan_shouldReturnUsers() {
        // given
        final User exampleUser = new User(
                "fn", "ln", LocalDate.of(2000, 10, 10), "e@a.c"
        );

        final LocalDate exampleDate = LocalDate.of(2000, 10, 10);

        when(userRepository.findByBirthdateAfter(exampleDate)).thenReturn(Set.of(exampleUser));

        // when
        final Set<User> result = userService.getUsersOlderThan(exampleDate);

        // then
        Assertions.assertEquals(Set.of(exampleUser), result);
        verify(userRepository).findByBirthdateAfter(exampleDate);
    }

}
