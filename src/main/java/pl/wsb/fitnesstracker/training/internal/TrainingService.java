package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;

/**
 * Service interface for managing training-related operations.
 * Handles creation, updating, retrieval, and business logic related to training sessions.
 */
interface TrainingService {

    /**
     * Creates a new training session based on the provided training request data.
     *
     * @param trainingDto the data transfer object containing details of the training to be created
     * @return the persisted {@link Training} entity
     */
    Training createTraining(final TrainingRequestDto trainingDto);

    /**
     * Updates an existing training session identified by the given ID using the provided training request data.
     *
     * @param trainingDto the data transfer object containing updated training information
     * @param trainingId  the ID of the training session to update
     * @return the updated {@link Training} entity
     */
    Training updateTraining(final TrainingRequestDto trainingDto, final Long trainingId);

}
