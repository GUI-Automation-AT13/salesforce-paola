package core.utils;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class StringDateConverterTests {

    @Test
    public void shouldReturnTodayDateWithTodayString() {
        String dateString = "TODAY";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(dateString);
        assertEquals(dateConverter.getDate(), LocalDate.now());
    }

    @Test
    public void shouldReturnTomorrowDateWithTomorrowString() {
        String tomorrow = "TOMORROW";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(tomorrow);
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnYesterdayDateWithYesterdayString() {
        String yesterday = "YESTERDAY";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(yesterday);
        LocalDate expectedDate = LocalDate.now().minusDays(1);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnStringDateAsDateWithSlash() {
        String date = "2021/05/13";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.parse("2021-05-13");
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnStringDateAsDate() {
        String date = "2021-05-13";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.parse(date);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateWithSpecificConditionsInString() {
        String date = "2 days ago";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().minusDays(2);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateTwoMonthsAgo() {
        String date = "2 months ago";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().minusMonths(2);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateYearAndMonthsAgo() {
        String date = "15 months from now";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().plusMonths(15);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateYearAndOneMonthAgo() {
        String date = "1 month from now";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().plusMonths(1);
        assertEquals(dateConverter.getDate(), expectedDate);
    }
}
