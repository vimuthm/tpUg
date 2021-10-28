package safeforhall.model.event;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventDate {
    public static final String MESSAGE_CONSTRAINTS = "EventDate inputted has to be in dd-mm-yyyy, dd.mm.yyyy"
            + "or dd/mm/yyyy format";
    public static final String DESC = "Date: ";

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter
            .ofPattern("[dd-MM-yyyy][dd.MM.yyyy][dd/MM/yyyy]");

    public final String eventDate;

    /**
     * Constructs a {@code EventDate}.
     *
     * @param date A valid date.
     */
    public EventDate(String date) {
        requireNonNull(date);
        checkArgument(isValidEventDate(date), MESSAGE_CONSTRAINTS);
        String[] dayMonthYear = date.split("[-./]");
        assert dayMonthYear.length == 3;
        this.eventDate = dayMonthYear[0] + "-" + dayMonthYear[1] + "-" + dayMonthYear[2];
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidEventDate(String date) {
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the current eventDate is in the past.
     */
    public boolean isPast() {
        return toLocalDate().isBefore(LocalDate.now());
    }

    public String getEventDate() {
        return eventDate;
    }

    /**
     * Converts the given {@code EventDate} to a {@code LocalDate}.
     */
    public LocalDate toLocalDate() {
        return LocalDate.parse(eventDate, dateFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDate // instanceof handles nulls
                && eventDate.equals(((EventDate) other).eventDate)); // state check
    }

    @Override
    public String toString() {
        return eventDate;
    }
}
