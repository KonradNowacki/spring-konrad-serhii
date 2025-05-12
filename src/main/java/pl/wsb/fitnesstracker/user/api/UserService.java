package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user in the system.
     *
     * @param user the user entity to be created
     * @return the created {@link User} with generated ID and persisted data
     */
    User createUser(User user);

    /**
     * Updates the user with the given ID using the provided data.
     *
     * @param userId the ID of the user to update
     * @param user the user entity containing updated information
     * @return the updated {@link User} entity
     */
    User updateUser(Long userId, User user);

    /**
     * Deletes the user with the specified ID.
     *
     * @param userId the ID of the user to delete
     */
    void deleteUserById(Long userId);


}
