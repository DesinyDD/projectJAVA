package packman.models.mails.information;

import packman.models.people.Officer;

import java.time.LocalDateTime;

public class TimeStamp {
    private Officer officer;
    private LocalDateTime dateTime;

    /* Constructor */
    public TimeStamp(Officer officer, LocalDateTime dateTime) {
        this.officer = officer;
        this.dateTime = dateTime;
    }
    public TimeStamp(Officer officer) {
        this(officer, LocalDateTime.now() );
    }

    /* Setter */
    public void setOfficer(Officer officer) { this.officer = officer; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setDateTime() { this.dateTime = LocalDateTime.now(); }

    /* Getter */
    public Officer getOfficer() { return officer; }
    public LocalDateTime getDateTime() { return dateTime; }
}
