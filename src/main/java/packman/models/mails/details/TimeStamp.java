package packman.models.mails.details;

import packman.models.account.details.Name;

import java.time.LocalDateTime;

public class TimeStamp {
    private Name officerName;
    private LocalDateTime dateTime;

    /* Constructor */
    public TimeStamp(Name officerName, LocalDateTime dateTime) {
        this.officerName = officerName;
        this.dateTime    = dateTime;
    }

    /* Setter */
    public void setOfficerName(Name officerName) { this.officerName = officerName; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    /* Getter */
    public LocalDateTime getDateTime() { return dateTime; }
    public Name getOfficerName() { return officerName; }

    public void setDateTime() { this.dateTime = LocalDateTime.now(); }
}
