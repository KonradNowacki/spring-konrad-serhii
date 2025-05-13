package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper class responsible for converting {@link Training} entities into {@link TrainingResponseDto} objects.
 */
@Component
class TrainingMapper {

    /**
     * Converts a {@link Training} entity to a {@link TrainingResponseDto}.
     *
     * @param entity the training entity to convert
     * @return the corresponding {@link TrainingResponseDto}
     */
    public TrainingResponseDto toDto(Training entity) {
        return new TrainingResponseDto(
                entity.getId(),
                toUserDto(entity.getUser()),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getActivityType(),
                entity.getDistance(),
                entity.getAverageSpeed()
        );
    }

    /**
     * Converts a {@link User} entity into a {@link UserDto} as part of a training mapping operation.
     *
     * @param user the user entity to convert
     * @return the corresponding {@link UserDto}
     */
    private UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

}
