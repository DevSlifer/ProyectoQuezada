package Utils;

import java.util.regex.Pattern;

/**
 * Validation utility class following Single Responsibility Principle.
 * Centralizes all validation logic to avoid code duplication.
 *
 * @author ProyectoQuezada Team
 */
public final class ValidationUtils {

    private ValidationUtils() {
        throw new UnsupportedOperationException("ValidationUtils is a utility class and cannot be instantiated");
    }

    /**
     * Validates that a string is not null and not empty after trimming.
     *
     * @param value The string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    /**
     * Validates an email address format.
     *
     * @param email The email to validate
     * @return true if valid email format, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (!isNotEmpty(email)) {
            return false;
        }
        return Pattern.matches(Constants.ValidationPatterns.EMAIL_PATTERN, email);
    }

    /**
     * Validates a Dominican cedula (11 digits).
     *
     * @param cedula The cedula to validate
     * @return true if valid format, false otherwise
     */
    public static boolean isValidCedula(String cedula) {
        if (!isNotEmpty(cedula)) {
            return false;
        }
        return Pattern.matches(Constants.ValidationPatterns.CEDULA_PATTERN, cedula);
    }

    /**
     * Validates a phone number (10 digits).
     *
     * @param phone The phone number to validate
     * @return true if valid format, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        if (!isNotEmpty(phone)) {
            return false;
        }
        return Pattern.matches(Constants.ValidationPatterns.PHONE_PATTERN, phone);
    }

    /**
     * Validates that a number string can be parsed as an integer.
     *
     * @param value The string to validate
     * @return true if can be parsed as integer, false otherwise
     */
    public static boolean isValidInteger(String value) {
        if (!isNotEmpty(value)) {
            return false;
        }
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that a number string can be parsed as a double.
     *
     * @param value The string to validate
     * @return true if can be parsed as double, false otherwise
     */
    public static boolean isValidDouble(String value) {
        if (!isNotEmpty(value)) {
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that an integer is positive (greater than 0).
     *
     * @param value The integer to validate
     * @return true if positive, false otherwise
     */
    public static boolean isPositive(int value) {
        return value > 0;
    }

    /**
     * Validates that a double is positive (greater than 0).
     *
     * @param value The double to validate
     * @return true if positive, false otherwise
     */
    public static boolean isPositive(double value) {
        return value > 0;
    }

    /**
     * Validates a string has a minimum length.
     *
     * @param value The string to validate
     * @param minLength The minimum length required
     * @return true if meets minimum length, false otherwise
     */
    public static boolean hasMinLength(String value, int minLength) {
        if (value == null) {
            return false;
        }
        return value.trim().length() >= minLength;
    }

    /**
     * Validates a string has a maximum length.
     *
     * @param value The string to validate
     * @param maxLength The maximum length allowed
     * @return true if within maximum length, false otherwise
     */
    public static boolean hasMaxLength(String value, int maxLength) {
        if (value == null) {
            return false;
        }
        return value.trim().length() <= maxLength;
    }

    /**
     * Creates a validation error message for an empty field.
     *
     * @param fieldName The name of the field
     * @return The error message
     */
    public static String getEmptyFieldMessage(String fieldName) {
        return "El campo de " + fieldName + " no debe estar vacío";
    }

    /**
     * Creates a validation error message for an invalid format.
     *
     * @param fieldName The name of the field
     * @return The error message
     */
    public static String getInvalidFormatMessage(String fieldName) {
        return "El formato del campo " + fieldName + " no es válido";
    }
}
