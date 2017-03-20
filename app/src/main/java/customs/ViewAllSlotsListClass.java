package customs;

/**
 * Created by sparsh on 28/11/16.
 */
public class ViewAllSlotsListClass {
    private String dateOfSlot;
    private String fromTime;
    private String toTime;
    private String aroundPlace;
    private String status;

    public ViewAllSlotsListClass(String dateOfSlot, String fromTime, String toTime, String aroundPlace) {
        this.dateOfSlot = dateOfSlot;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.aroundPlace = aroundPlace;
        this.status="No Allotment";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfSlot() {
        return dateOfSlot;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public String getAroundPlace() {
        return aroundPlace;
    }

    public String getStatus() {
        return status;
    }


}
