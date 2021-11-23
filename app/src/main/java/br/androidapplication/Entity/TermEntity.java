package br.androidapplication.Entity;

import androidx.room.*;

@Entity(tableName="term_table")

public class TermEntity {

    @PrimaryKey
    private int termID;

    private String termTitle;
    private String termStart;
    private String termEnd;

    public TermEntity (String termTitle, String termStart, String termEnd) {
        this.termTitle = termTitle;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termTitle=" + termTitle +
                ", termStart='" + termStart + '\'' +
                ", termEnd=" + termEnd +
                '}';
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
