package cams.interfaces;

import java.time.LocalDate;

public interface DateInput {
    LocalDate getValidDate(String title);
}
