package app.lib;

import java.sql.Date;

public class Request {

    //Attributes
    private int id;
    private int user_id;
    private String requested_type;
    private String requested_items;
    private String given_time;
    private String return_time;
    private Date requested_date;
    private String location;
    private String notes;
    private String status;
    private String signature;
    private int inventory_id;

    public Request(int id, int user_id, String requested_type, String requested_items, String given_time, String return_time, Date requested_date, String location, String notes, String status, String signature, int inventory_id) {
        this.id = id;
        this.user_id = user_id;
        this.requested_type = requested_type;
        this.requested_items = requested_items;
        this.given_time = given_time;
        this.return_time = return_time;
        this.requested_date = requested_date;
        this.location = location;
        this.notes = notes;
        this.status = status;
        this.signature = signature;
        this.inventory_id = inventory_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRequested_type() {
        return requested_type;
    }

    public void setRequested_type(String requested_type) {
        this.requested_type = requested_type;
    }

    public String getRequested_items() {
        return requested_items;
    }

    public void setRequested_items(String requested_items) {
        this.requested_items = requested_items;
    }

    public String getGiven_time() {
        return given_time;
    }

    public void setGiven_time(String given_time) {
        this.given_time = given_time;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public Date getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(Date requested_date) {
        this.requested_date = requested_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public void CreateRequest(){}

    public void EditRequest(){}

    public void DeleteRequest(){}

    public void ViewRequest(){}

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", requested_type='" + requested_type + '\'' +
                ", requested_items='" + requested_items + '\'' +
                ", given_time='" + given_time + '\'' +
                ", return_time='" + return_time + '\'' +
                ", requested_date=" + requested_date +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                ", signature='" + signature + '\'' +
                ", inventory_id=" + inventory_id +
                '}';
    }
}
