package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;

// TODO javadocs
interface TrainingService {

    // TODO javadocs
    Training createTraining(final TrainingRequestDto trainingDto);
    Training updateTraining(final TrainingRequestDto trainingDto, final Long trainingId);

}
