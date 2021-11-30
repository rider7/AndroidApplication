package br.androidapplication.Entity;
import androidx.room.*;

@Entity(tableName="assessment_table")
public class AssessmentEntity {

    @PrimaryKey
    private int assessmentID;

    private int courseAssessmentID;
    private String assessmentType; //Objective or Performance
    private String courseTitle;
    private String courseEnd;


    public AssessmentEntity(int assessmentID, int courseAssessmentID, String assessmentType, String courseTitle, String courseEnd) {
        this.assessmentID = assessmentID;
        this.courseAssessmentID = courseAssessmentID;
        this.assessmentType = assessmentType;
        this.courseTitle = courseTitle;
        this.courseEnd = courseEnd;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentID=" + assessmentID +
                ", courseAssessmentID=" + courseAssessmentID +
                ", assessmentType='" + assessmentType + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseEnd='" + courseEnd + '\'' +
                '}';
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseAssessmentID() {
        return courseAssessmentID;
    }

    public void setCourseAssessmentID(int courseAssessmentID) {
        this.courseAssessmentID = courseAssessmentID;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }
}
