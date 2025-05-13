package pl.wsb.fitnesstracker.training.internal;

import lombok.Getter;

/**
 * Enum representing different types of physical activities.
 * Each enum constant has a display name used for user-friendly representation.
 */
@Getter
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    ActivityType(String displayName) {
        this.displayName = displayName;
    }

}
