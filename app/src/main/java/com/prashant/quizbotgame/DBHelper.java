package com.prashant.quizbotgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Question.sqlite";
    public static final String Question = "Question";
    public static final String QuesID  = "quesID";
    public static final String QuesName = "quesName";
    public static final String QuesDetail = "quesDetail";
    public static final String Option1 = "option1";
    public static final String Option2 = "option2";
    public static final String Option3 = "option3";
    public static final String Option4 = "option4";
    public static final String C_Option = "c_Option";
    public static final String QCategory = "QCategory";
    public static final String QDone = "QDone";
    public static int DATABASE_VERSION = 1;

    private HashMap hp;
    private Context currentContext;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        currentContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                "create table Question " +
                        "(quesID integer primary key autoincrement, quesName text,quesDetail text,option1 text, option2 text,option3 text, option4 text, c_Option text, QCategory text, QDone text default \"N\")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Question");
        onCreate(db);
    }
    public boolean addQuestion(String quesName,String quesDetail,String option1,String option2, String option3, String option4, String c_Option, String QCategory, String QDone){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("quesName",quesName);
        contantValues.put("quesDetail", quesDetail);
        contantValues.put("option1",option1);
        contantValues.put("option2",option2);
        contantValues.put("option3",option3);
        contantValues.put("option4",option4);
        contantValues.put("c_Option",c_Option);
        contantValues.put("QCategory",QCategory);
        contantValues.put("QDone",QDone);
        db.insert("Question", null, contantValues);
        db.close();
        return true;
    }
    public boolean updateTable(List<Question> QuestionList)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        db.close();
        return true;
    }
    public boolean updateQuestion(Integer quesID,String quesName,String quesDetail,String option1,String option2, String option3, String option4, String c_Option, String QCategory, String QDone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contantValues = new ContentValues();
        contantValues.put("quesName",quesName);
        contantValues.put("quesDetail", quesDetail);
        contantValues.put("option1",option1);
        contantValues.put("option2",option2);
        contantValues.put("option3",option3);
        contantValues.put("option4",option4);
        contantValues.put("c_Option",c_Option);
        contantValues.put("QCategory",QCategory);
        contantValues.put("QDone",QDone);
        db.update("Question", contantValues, "quesID = ?", new String[]{Integer.toString(quesID)});
        db.close();
        return true;
    }

    public boolean updateSingleQuestionDone(Integer quesID, String QDone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String updateDoneString = "UPDATE Question SET QDone = '" + QDone + "' WHERE quesID = " + quesID;
        db.execSQL(updateDoneString);
        return true;
    };

    public Integer deleteQuestion(Integer quesID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Question","quesID = ?",new String[]{Integer.toString(quesID)});
    }
    public Cursor getData(int quesID){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from Question where quesID = " + quesID + "", null);
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db=this.getWritableDatabase();
        int numRows=(int) DatabaseUtils.queryNumEntries(db,Question);
        return numRows;
    }
    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> arraylist= new ArrayList<Question>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from Question",null);

        if (cursor.moveToFirst()) {
            do {
                arraylist.add(new Question(cursor.getString(cursor.getColumnIndex(QuesID)),
                        cursor.getString(cursor.getColumnIndex(QuesName)),
                        cursor.getString(cursor.getColumnIndex(QuesDetail)),
                        cursor.getString(cursor.getColumnIndex(Option1)),
                        cursor.getString(cursor.getColumnIndex(Option2)),
                        cursor.getString(cursor.getColumnIndex(Option3)),
                        cursor.getString(cursor.getColumnIndex(Option4)),
                        cursor.getString(cursor.getColumnIndex(C_Option)),
                        cursor.getString(cursor.getColumnIndex(QCategory)),
                        cursor.getString(cursor.getColumnIndex(QDone))
                        ));
//                arraylist.add(cursor.getString(cursor.getColumnIndex(QuesName)));
            } while (cursor.moveToNext());
        }
        return arraylist;
    }


    public ArrayList<Question> findQuestionByCategory(String category){
        ArrayList<Question> arrayList = new ArrayList<Question>();
        SQLiteDatabase db = this.getReadableDatabase();{
            Cursor cursor = db.rawQuery("SELECT * FROM Question WHERE QCategory = \""+category+"\"", null);
            if(cursor.moveToFirst()){
                do{
                    arrayList.add(new Question(cursor.getString(cursor.getColumnIndex(QuesID)),
                            cursor.getString(cursor.getColumnIndex(QuesName)),
                            cursor.getString(cursor.getColumnIndex(QuesDetail)),
                            cursor.getString(cursor.getColumnIndex(Option1)),
                            cursor.getString(cursor.getColumnIndex(Option2)),
                            cursor.getString(cursor.getColumnIndex(Option3)),
                            cursor.getString(cursor.getColumnIndex(Option4)),
                            cursor.getString(cursor.getColumnIndex(C_Option)),
                            cursor.getString(cursor.getColumnIndex(QCategory)),
                            cursor.getString(cursor.getColumnIndex(QDone))
                    ));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    };

    public ArrayList<String> AllCategories(){
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();{
            Cursor cursor = db.rawQuery("SELECT QCategory FROM Question GROUP BY QCategory", null);
            if(cursor.moveToFirst()){
                do{
                    arrayList.add(cursor.getString(cursor.getColumnIndex(QCategory)));
                } while (cursor.moveToNext());
            }
        }
        return arrayList;
    };
}
