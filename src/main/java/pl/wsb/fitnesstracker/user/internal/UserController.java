package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // TODO: Implement the method to add a new user.
        //  You can use the @RequestBody annotation to map the request body to the UserDto object.


        return null;
    }

    @GetMapping("/simple")
    public List<UserDto> getSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        User user = userService.getUser(id).orElseThrow();
        return userMapper.toDto(user);
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email).stream()
                .map(userMapper::toDto)
                .toList();
    }


}