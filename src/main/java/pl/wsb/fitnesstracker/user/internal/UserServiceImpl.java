package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(final Long userId, final User newUserData) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + "doesn't exist."));

        user.setFirstName(newUserData.getFirstName());
        user.setLastName(newUserData.getLastName());
        user.setEmail(newUserData.getEmail());
        user.setBirthdate(newUserData.getBirthdate());

        return userRepository.save(user);
    }

    @Override
    public Set<User> getUsersOlderThan(final LocalDate date) {
        return userRepository.findByBirthdateAfter(date);
    }

    @Override
    public void deleteUserById(final Long userId) {
        userRepository.deleteById(userId);
    }


}