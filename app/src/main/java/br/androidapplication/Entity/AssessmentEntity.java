package br.androidapplication.Entity;
import androidx.room.*;

@Entity(tableName="assessment_table")
public class AssessmentEntity {

    @PrimaryKey
    private int assessmentID;

    private int courseAssessmentID;
    private String assessmentType; //Objective or Performance
    private String assessmentTitle;
    private String assessmentEnd;

    public AssessmentEntity(int assessmentID, int courseAssessmentID, String assessmentType, String assessmentTitle, String assessmentEnd) {
        this.assessmentID = assessmentID;
        this.courseAssessmentID = courseAssessmentID;
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.assessmentEnd = assessmentEnd;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentID=" + assessmentID +
                ", courseAssessmentID=" + courseAssessmentID +
                ", assessmentType='" + assessmentType + '\'' +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentEnd='" + assessmentEnd + '\'' +
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

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }
}
