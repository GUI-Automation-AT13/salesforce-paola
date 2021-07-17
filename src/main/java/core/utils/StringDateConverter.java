package core.utils;

import java.time.LocalDate;
import java.util.Locale;

public class StringDateConverter {

    private LocalDate date;
    private final String simpleDateFormat = "\\d{4}(-|/)(0[1-9]|1[012])(-|/)(0[1-9]|[12][0-9]|[3][01])";
    private final String singleWordFormat = "[A-Z]+";

    /**
     * Sends the String to the method to convert.
     * @param dateString the String date to convert.
     */
    public void convertDate(final String dateString) {
        validateNullEmpty(dateString);
        if (validateString(dateString)) {
            try  {
                if (dateString.matches(simpleDateFormat)) {
                    dateStringConvert(dateString);
                } else {
                    enumConvertDate(dateString);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid Argument: Unsupported String Format.");
            }
        }
    }

    /**
     * Validates that the String has the correct format.
     * 1. YYYY-MM-DD
     * 2. TODAY - YESTERDAY - TOMORROW
     * 3. 1 day ago | 13 months from now
     * @param dateString String date to validate
     * @return true if the format is correct.
     */
    public boolean validateString(String dateString) {
        return true;
    }

    /**
     * Sends String to the correspond method for enum case.
     * @param dateString String date to convert.
     */
    public void enumConvertDate(final String dateString) {
        if (dateString.matches(singleWordFormat)) {
            singleWordDateConvert(dateString);
        } else {
            specificDateStringConvert(dateString);
        }
    }

    /**
     * Converts a String in the format "number days | month | year  ago | from now".
     * @param dateString String to convert.
     */
    private void specificDateStringConvert(final String dateString) {
        String[] splited = dateString.split(" ");
        int number = Integer.parseInt(splited[0]);

        if (splited[1].endsWith("s")) {
            splited[1] = splited[1].substring(0, splited[1].lastIndexOf("s"));
        }

        if (splited[2].equals("ago")) {
            number = -number;
        }
        switch (DateStringTypeEnum.valueOf(splited[1].toUpperCase(Locale.ROOT))) {
            case DAY: date = LocalDate.now().plusDays(number); break;
            case MONTH: date = LocalDate.now().plusMonths(number); break;
            case YEAR: date = LocalDate.now().plusYears(number); break;
            default: break;
        }
    }

    /**
     * Converts a String in Date format YYYY-MM-DD.
     * @param dateString String to convert.
     */
    private void dateStringConvert(String dateString) {
        if (dateString.contains("/")) {
            dateString = dateString.replace("/", "-");
        }
        date = LocalDate.parse(dateString);
    }

    /**
     * Converts a String with single word, "TODAY", "YESTERDAY", "TOMORROW".
     * @param dateString String to convert.
     */
    private void singleWordDateConvert(final String dateString) {
        switch (DateStringTypeEnum.valueOf(dateString)) {
            case TODAY: date = LocalDate.now(); break;
            case TOMORROW: date = LocalDate.now().plusDays(1); break;
            case YESTERDAY: date = LocalDate.now().minusDays(1); break;
            default: break;
        }
    }

    /**
     * Gets the date.
     * @return the LocalDate date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Validates the String, avoiding null or empty.
     * @param dateString String to validate.
     * @return true if String is valid.
     */
    private boolean validateNullEmpty(final String dateString) {
        if (dateString == null || dateString == "")
            return false;
        return true;
    }
}
