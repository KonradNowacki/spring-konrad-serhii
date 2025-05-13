package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

@Component
class TrainingMapper {

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

//    public Training toEntity(TrainingDto dto) {
//        return new Training(
//                toUserEntity(dto.user()),
//                dto.startTime(),
//                dto.endTime(),
//                dto.activityType(),
//                dto.distance(),
//                dto.averageSpeed()
//        );
//    }

    private UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }
//
//    private User toUserEntity(UserDto userDto) {
//        return new User(
//                userDto.firstName(),
//                userDto.lastName(),
//                userDto.birthdate(),
//                userDto.email());
//    }

}
