package pl.wsb.fitnesstracker.user.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds all users born after the specified date.
     *
     * @param birthdateAfter the {@link LocalDate} after which users were born
     * @return a {@link Set} of {@link User} entities with birthdates after the given date
     */
    Set<User> findByBirthdateAfter(LocalDate birthdateAfter);

    /**
     * Finds a user by email address, ignoring case.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the {@link User} if found, or empty if not
     */
    Optional<User> findByEmailIgnoreCase(String email);

}
