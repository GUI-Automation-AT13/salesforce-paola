package core.utils;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class StringDateConverterTests {

    @Test
    public void shouldReturnTodayDateWithTodayString() throws Exception {
        String dateString = "TODAY";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(dateString);
        assertEquals(dateConverter.getDate(), LocalDate.now());
    }

    @Test
    public void shouldReturnTomorrowDateWithTomorrowString() throws Exception {
        String tomorrow = "TOMORROW";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(tomorrow);
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnYesterdayDateWithYesterdayString() throws Exception {
        String yesterday = "YESTERDAY";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(yesterday);
        LocalDate expectedDate = LocalDate.now().minusDays(1);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnStringDateAsDate() throws Exception {
        String date = "2021-05-13";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.parse(date);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateWithSpecificConditionsInString() throws Exception {
        String date = "2 days ago";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().minusDays(2);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateTwoMonthsAgo() throws Exception {
        String date = "2 months ago";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().minusMonths(2);
        assertEquals(dateConverter.getDate(), expectedDate);
    }

    @Test
    public void shouldReturnDateYearAndMonthsAgo() throws Exception {
        String date = "15 months from now";
        StringDateConverter dateConverter = new StringDateConverter();
        dateConverter.convertDate(date);
        LocalDate expectedDate = LocalDate.now().plusMonths(15);
        assertEquals(dateConverter.getDate(), expectedDate);
    }
}
