package core.utils;

import java.time.LocalDate;

public class StringDateConverter {

    private LocalDate date;
    private final String formatDate = "MM/dd/YYYY";

    /**
     * Validates and sends String to the correct method to convert.
     * @param dateString String to convert.
     * Format: YYYY-MM-DD, "TODAY", "TOMORROW", "YESTERDAY",
     * "number days | months | years  ago | from now".
     * @throws Exception if String is not valid.
     */
    public void convertDate(final String dateString) throws Exception {
        if (validString(dateString)) {
            if (dateString.contains("-")) {
                dateStringConvert(dateString);
            }
            if (dateString.contains(" ")) {
                specificDateStringConvert(dateString);
            }
            if (!dateString.contains(" ") && !dateString.contains("-")) {
                singleWordDateConvert(dateString);
            }
        } else {
            throw new Exception("The String can't be null or empty.");
        }
    }

    /**
     * Converts a String in the format "number days | month | year  ago | from now".
     * @param dateString String to convert.
     */
    private void specificDateStringConvert(final String dateString) {
        String[] splited = dateString.split(" ");
        int number = Integer.parseInt(splited[0]);
        if (splited[2].equals("ago")) {
            number = -number;
        }
        switch (splited[1]) {
            case "days":date = LocalDate.now().plusDays(number); break;
            case "months":date = LocalDate.now().plusMonths(number); break;
            case "years":date = LocalDate.now().plusYears(number); break;
            default: break;
        }
    }

    /**
     * Converts a String in Date format YYYY-MM-DD.
     * @param dateString String to convert.
     */
    private void dateStringConvert(final String dateString) {
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
    private boolean validString(final String dateString) {
        if (dateString == null || dateString == "")
            return false;
        else
            return true;
    }
}
