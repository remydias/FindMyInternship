package Model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class OfferFavoriteDatabase extends SQLiteOpenHelper{
        
        private static final String USER_TABLE = "Offer_fav_table";
        private static final String USER_ID = "ID";
        private static final String USER_STUD = "Student";
        private static final String USER_OFFID = "Offid";
        
        private static final String CREATE_BDD ="CREATE TABLE  Offer_fav_table ( ID  INTEGER PRIMARY KEY AUTOINCREMENT, Student  TEXT NOT NULL, Offid  TEXT NOT NULL);";


        public OfferFavoriteDatabase(Context context, String name, CursorFactory factory,int version) {
                super(context, name, factory, version);
                // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
                db.execSQL(CREATE_BDD);
                
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
        

}