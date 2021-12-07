package br.androidapplication.Entity;
import androidx.room.*;

@Entity(tableName="course_table")
public class CourseEntity {

    @PrimaryKey
    private int courseID;

    private int courseTermID;
    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String instructorName;
    private String instructorNumber;
    private String instructorAddress;
    private String notes;

    public CourseEntity(int courseID, int courseTermID, String courseTitle, String courseStart, String courseEnd, String courseStatus, String instructorName, String instructorNumber, String instructorAddress, String notes) {
        this.courseID = courseID;
        this.courseTermID = courseTermID;
        this.courseTitle = courseTitle;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.instructorName = instructorName;
        this.instructorNumber = instructorNumber;
        this.instructorAddress = instructorAddress;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseID=" + courseID +
                ", courseTermID=" + courseTermID +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseStart='" + courseStart + '\'' +
                ", courseEnd='" + courseEnd + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorNumber='" + instructorNumber + '\'' +
                ", instructorAddress='" + instructorAddress + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorNumber() {
        return instructorNumber;
    }

    public void setInstructorNumber(String instructorNumber) {
        this.instructorNumber = instructorNumber; }

    public String getInstructorAddress() {
        return instructorAddress;
    }

    public void setInstructorAddress(String instructorAddress) {
        this.instructorAddress = instructorAddress; }

    public int getCourseTermID() {
        return courseTermID;
    }

    public void setCourseTermID(int courseTermID) {
        this.courseTermID = courseTermID;
    }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }
}
