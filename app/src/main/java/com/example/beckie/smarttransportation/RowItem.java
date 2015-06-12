package com.example.beckie.smarttransportation;

/**
 * Created by Beckie on 4/30/2015.
 */
public class RowItem {
    private String route;
    private String time_of_dep;
    private String particulars;
    private String venue;
    private String posterID;
    private String backgroundPhoto;
    private int rowID;
    private String datePosted;
    private String timeOfEvent;
    private String dateOfEvent;
    private String animalID;
    private String eventPhoto;

    public RowItem(String route, String time_of_dep,
                   String particulars, String posterID, String datePosted,
                   String timeOfEvent, String dateOfEvent, int rowID, String animalID, String eventPhoto) {
        this.route = route;
        this.time_of_dep = time_of_dep;
        this.particulars = particulars;
        this.posterID = posterID;
        this.datePosted = datePosted;
        this.timeOfEvent = timeOfEvent;
        this.dateOfEvent = dateOfEvent;
        this.rowID = rowID;
        this.animalID = animalID;
        this.eventPhoto = eventPhoto;
    }

    public String  getEventPhoto() {
        return eventPhoto;
    }

    public void setEventPhoto(String eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    public String  getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public int getRowId() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }


    public String getRoute() {
        return route;
    }

    public String getDatePosted() {
        return datePosted;
    }


    public String getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(String time) {
        this.timeOfEvent = time;
    }

    public String getDateOfEvent() {

        return dateOfEvent;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getTime_of_dep() {
        return time_of_dep;
    }

    public void setTime_of_dep(String time_of_dep) {
        this.time_of_dep = time_of_dep;
    }

    public String getPosterID() {
        return posterID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    public String getVenue() {
        return venue;
    }

    public String setDate(String date) {
        return this.venue = date;
    }

    @Override
    public String toString() {
        return time_of_dep + "\n" + particulars;
    }
}