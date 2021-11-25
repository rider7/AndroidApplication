package br.androidapplication.Entity;

import androidx.room.*;

//Annotated class used to describe the table "term_table"
@Entity(tableName="term_table")
public class TermEntity {
//Do I need this Primary Key?
    @PrimaryKey(autoGenerate = true)
    private int termID;
    //table column names and data type
    private String termTitle;
    private String termStart;
    private String termEnd;

    //Constructor
    public TermEntity (int termID, String termTitle, String termStart, String termEnd) {
        this.termID = termID;
        this.termTitle = termTitle;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termID" + termID +
                "termTitle=" + termTitle +
                ", termStart='" + termStart + '\'' +
                ", termEnd=" + termEnd +
                '}';
    }
    //Getters and Setters
    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }
}
