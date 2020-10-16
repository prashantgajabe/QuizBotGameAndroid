package com.prashant.quizbotgame;


public class Question {
    private String QuesID, QuesName, QuesDetail, Option1, Option2, Option3, Option4, C_Option, QCategory, Done;

    public Question(String quesID, String quesName, String quesDetail, String option1, String option2, String option3, String option4, String c_Option, String QCategory, String Done) {
        QuesID = quesID;
        QuesName = quesName;
        QuesDetail = quesDetail;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        C_Option = c_Option;
        this.QCategory = QCategory;
        this.Done = Done;
    }

    public String getDone() {
        return Done;
    }

    public void setDone(String done) {
        Done = done;
    }

    public void setQuesID(String quesID) {
        QuesID = quesID;
    }

    public void setQuesName(String quesName) {
        QuesName = quesName;
    }

    public void setQuesDetail(String quesDetail) {
        QuesDetail = quesDetail;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public void setC_Option(String c_Option) {
        C_Option = c_Option;
    }

    public void setQCategory(String QCategory) {
        this.QCategory = QCategory;
    }

    public String getQuesID() {
        return QuesID;
    }

    public String getQuesName() {
        return QuesName;
    }

    public String getQuesDetail() {
        return QuesDetail;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getC_Option() {
        return C_Option;
    }

    public String getQCategory() {
        return QCategory;
    }
}