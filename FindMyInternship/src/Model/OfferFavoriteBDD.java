package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class OfferFavoriteBDD {
	
        private static final int VERSION_BDD = 1;
        private static final String NOM_BDD = "FMI4.db";
        private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
        
        private static final String USER_TABLE = "Offer_fav_table";
        private static final String USER_ID = "ID";
        private static final int NUM_USER_ID = 0;
        private static final String USER_STUD = "Student";
        private static final int NUM_USER_STUD= 1;
        private static final String USER_OFFID = "Offid";
        private static final int NUM_USER_OFFID = 2;
 
        private SQLiteDatabase bdd;
 
        private OfferFavoriteDatabase maBaseSQLite;
 
        public OfferFavoriteBDD(Context context){
                //On créer la BDD et sa table
                maBaseSQLite = new OfferFavoriteDatabase(context, NOM_BDD, null, VERSION_BDD);
        }
 
        public void open(){
                //on ouvre la BDD en écriture
                bdd = maBaseSQLite.getWritableDatabase();
        }
 
        public void close(){
                //on ferme l'accès à la BDD
                bdd.close();
        }
 
        public SQLiteDatabase getBDD(){
                return bdd;
        }
 
        public long insertOfferFav(OfferFav stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                ContentValues values = new ContentValues();
                
                //this.open();
                
                
                
                OfferFav[] myOffFav = getOffersFavByStudent(stud.getStudID());
                
                if(myOffFav != null)
                {
                	for(int i=0;i<myOffFav.length;i++)
                	{
                		if(myOffFav[i].getOfferID() == stud.getOfferID())
                		{
                			return -2;
                		}
                	}
                }
                
                
                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                values.put(USER_STUD, stud.getStudID());
                values.put(USER_OFFID, stud.getOfferID());
                //on insère l'objet dans la BDD via le ContentValues
                return bdd.insert(USER_TABLE, null, values);
        }
        
        private boolean checkDataBase() {
            SQLiteDatabase checkDB = null;
            try {
                checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
                checkDB.close();
            } catch (SQLiteException e) {
                // base de données n'existe pas.
            }
            return checkDB != null ? true : false;
        }
        
        public boolean containsOfferFav(int StudID, int OffID)
        {
        	OfferFav[] myOffFav = getOffersFavByStudent(StudID);
            
            if(myOffFav != null)
            {
            	for(int i=0;i<myOffFav.length;i++)
            	{
            		if(myOffFav[i].getOfferID() == OffID)
            		{
            			return true;
            		}
            	}
            }
            return false;
        }
 
        public int updateOfferFav(int id, OfferFav stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                                ContentValues values = new ContentValues();
                                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                                values.put(USER_STUD, stud.getStudID());
                                values.put(USER_OFFID, stud.getOfferID());
                                
                                
                return bdd.update(USER_TABLE, values,USER_ID + " = " +id, null);
        }
 
        public int removeOfferFavByID(int id){
                //Suppression d'un livre de la BDD grâce à l'ID
                return bdd.delete(USER_TABLE, USER_ID + " = " +id, null);
        }
 
        public OfferFav getOfferFavByOffID(int OFFID){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_STUD,USER_OFFID},USER_OFFID + " LIKE \"" + OFFID +"\"",null,  null, null, null,null);
                return cursorToOfferFav(c);
        }
           
        
        public OfferFav[] getOffersFavByStudent(int ID){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_STUD,USER_OFFID}, USER_STUD + " LIKE \"" + ID +"\"", null, null, null, null,null);
                return cursorsToOffers(c);
        }
        
 
        //Cette méthode permet de convertir un cursor en un livre
        private OfferFav cursorToOfferFav(Cursor c){
                //si aucun élément n'a été retourné dans la requête, on renvoie null
                if (c.getCount() == 0)
                        return null;
 
                //Sinon on se place sur le premier élément
                c.moveToFirst();
                //On créé un livre
                OfferFav stud = new OfferFav();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                stud.setID(c.getInt(NUM_USER_ID));
                stud.setStudID(c.getInt(NUM_USER_STUD));
                stud.setOfferID(c.getInt(NUM_USER_OFFID));
                
                //On ferme le cursor
                c.close();
 
                //On retourne le livre
                return stud;
        }
        
        public OfferFav[] cursorsToOffers(Cursor c)
    	{
        	OfferFav[] liste = new OfferFav[c.getCount()]; 
        	//si aucun élément n'a été retourné dans la requête, on renvoie null 
        	if (c.getCount() == 0) 
        	{ 
        		OfferFav s = new OfferFav(); 
        		Log.e("skat", "Aucune donnée a transtyper"); 
        		return(null); 
        
        	} 
        	else 
        	{ 
        		//Sinon on se place sur le premier élément 
        		c.moveToFirst(); 
        		
        		for (int i = 0; i < c.getCount(); i++)  
        		{ 
        			OfferFav stud = new OfferFav(); 
        			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        			stud.setID(c.getInt(NUM_USER_ID));
        			stud.setStudID(c.getInt(NUM_USER_STUD));
        			stud.setOfferID(c.getInt(NUM_USER_OFFID));
    	   
        			liste[i]=stud; 
        			c.moveToNext(); 
        		} 
        		return liste; 
        	} 
     }
}