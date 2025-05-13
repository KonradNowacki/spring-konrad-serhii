package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;

/**
 * Data Transfer Object used for creating or updating a training session.
 * This DTO represents the payload sent from the client when submitting training data.
 *
 * @param userId        the ID of the user associated with the training
 * @param startTime     the start time of the training session
 * @param endTime       the end time of the training session
 * @param activityType  the type of physical activity performed
 * @param distance      the total distance covered during the training in kilometers
 * @param averageSpeed  the average speed of the training in kilometers per hour
 */
record TrainingRequestDto(
        Long userId,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {

}
