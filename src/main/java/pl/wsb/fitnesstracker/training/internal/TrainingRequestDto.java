package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;

// TODO java doc
record TrainingRequestDto(
        Long userId,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {

}
