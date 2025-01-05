package com.example.projectfx.exceptions;

/**
 * Custom exception class for the ecosystem simulation.
 */
public class EcosystemException extends Exception {

    /**
     * Constructs a new EcosystemException with the specified detail message.
     *
     * @param message The detail message.
     */
    public EcosystemException(String message) {
        super(message);
    }

    /**
     * Constructs a new EcosystemException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public EcosystemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new EcosystemException with the specified cause.
     *
     * @param cause The cause of the exception.
     */
    public EcosystemException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new EcosystemException with no detail message or cause.
     */
    public EcosystemException() {
        super("An unspecified error occurred in the ecosystem.");
    }

    /**
     * Returns a detailed description of the exception, including its message and cause.
     *
     * @return A detailed string describing the exception.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EcosystemException: ").append(getMessage());
        if (getCause() != null) {
            sb.append("\nCaused by: ").append(getCause().toString());
        }
        return sb.toString();
    }

    /**
     * Suggests a possible solution for the exception based on the message.
     *
     * @return A string suggesting a possible solution.
     */
    public String suggestSolution() {
        if (getMessage() == null) {
            return "No specific solution available.";
        }

        if (getMessage().contains("energy")) {
            return "Check the energy levels of species and ensure they are consuming enough resources.";
        } else if (getMessage().contains("environment")) {
            return "Verify the environment setup and ensure it is initialized properly.";
        } else if (getMessage().contains("resource")) {
            return "Ensure the resource is available and not fully consumed.";
        } else {
            return "Refer to the ecosystem logs for more details.";
        }
    }
}
