package Model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CompanyDataBase extends SQLiteOpenHelper{
        
        private static final String USER_TABLE = "Company_table";
        private static final String USER_ID = "ID";
        private static final String USER_LASTNAME = "Name";
        private static final String USER_FIRSTNAME = "Firstname";
        private static final String USER_MAIL = "Email";
        private static final String USER_COMPANY = "Company";
        private static final String USER_Pwd = "Password";
        private static final String USER_DESC = "Description";
        
        //private static final String CREATE_BDD = "CREATE TABLE " + USER_TABLE + " ("
                        //+ USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_LASTNAME + " TEXT NOT NULL, "
                        //+ USER_FIRSTNAME + " TEXT NOT NULL, "+USER_MAIL + "TEXT NOT NULL, "+USER_COMPANY+"TEXT NOT NULL);";
        
        private static final String CREATE_BDD ="CREATE TABLE  Company_table ( ID  INTEGER PRIMARY KEY AUTOINCREMENT,  Name  TEXT NOT NULL, Firstname  TEXT NOT NULL, Email TEXT NOT NULL, Company TEXT NOT NULL, Password TEXT NOT NULL, Description TEXT NOT NULL);";

        
       // private static final String CREATE_BDD ="CREATE TABLE  Company_table ( ID  INTEGER PRIMARY KEY AUTOINCREMENT,  Name  TEXT NOT NULL, Firstname  TEXT NOT NULL, Email TEXT NOT NULL, Company TEXT NOT NULL, Password TEXT NOT NULL, Description TEXT NOT NULL);";


        public CompanyDataBase(Context context, String name, CursorFactory factory,int version) {
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