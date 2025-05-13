package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;


    /**
     * Retrieves all users with full details.
     *
     * @return a list of {@link UserDto} objects representing all users
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers().stream().map(userMapper::toDto).toList();
    }

    /**
     * Creates a new user.
     *
     * @param userDto the user data to create
     * @return the created user as {@link UserDto}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        final User createdUser = userService.createUser(userMapper.toEntity(userDto));
        return userMapper.toDto(createdUser);
    }

    /**
     * Updates an existing user by ID.
     *
     * @param userId the ID of the user to update
     * @param userDto the updated user data
     * @return the updated user as {@link UserDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        final User updatedUser = userService.updateUser(userId, userMapper.toEntity(userDto));
        return userMapper.toDto(updatedUser);
    }

    /**
     * Retrieves a simplified list of all users.
     *
     * @return a list of {@link SimpleUserDto} objects
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers().stream().map(userMapper::toSimpleUserDto).toList();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user as {@link UserDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        User user = userService.getUser(id).orElseThrow();
        return userMapper.toDto(user);
    }

    /**
     * Retrieves users by their email address.
     *
     * @param email the email address to search for
     * @return a list of {@link UserDto} objects matching the email
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email).stream().map(userMapper::toDto).toList();
    }

    /**
     * Retrieves users older than a specific date.
     *
     * @param date the cutoff date (users born before this are included)
     * @return a set of {@link UserDto} representing users older than the given date
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/older/{date}")
    public Set<UserDto> getAllUsersOlderThan(@PathVariable("date") LocalDate date) {
        return userService.getUsersOlderThan(date).stream().map(userMapper::toDto).collect(Collectors.toSet());
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }


}