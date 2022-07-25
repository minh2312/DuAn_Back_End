package ml.duAn.api.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetCurDate {
    private Date dateTime;

    public GetCurDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        this.dateTime = c.getTime();
    }

    public Date getDateTime() {
        return dateTime;
    }
}
