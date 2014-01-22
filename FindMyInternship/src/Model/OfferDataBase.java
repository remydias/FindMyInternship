package Model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class OfferDataBase extends SQLiteOpenHelper{
        
        private static final String USER_TABLE = "Offer_table";
        private static final String USER_ID = "ID";
        private static final String USER_COMP = "Company";
        private static final String USER_TITLE = "Title";
        private static final String USER_DESC = "Description";
        private static final String USER_SKILLS = "Skills";
        private static final String USER_PROFILE = "Profile";
        private static final String USER_DURATION = "Duration";
        private static final String USER_SALARY = "Salary";
        
        private static final String CREATE_BDD ="CREATE TABLE  Offer_table ( ID  INTEGER PRIMARY KEY AUTOINCREMENT, Company  TEXT NOT NULL, Title  TEXT NOT NULL, Description  TEXT NOT NULL, Skills TEXT NOT NULL, Profile TEXT NOT NULL, Duration TEXT NOT NULL, Salary TEXT NOT NULL);";


        public OfferDataBase(Context context, String name, CursorFactory factory,int version) {
                super(context, name, factory, version);
                // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
                db.execSQL(CREATE_BDD);
                
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO Auto-generated method stub
                
        }
        

}