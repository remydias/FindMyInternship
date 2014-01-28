package Model;

import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class ApplicationBDD {
	private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "FMI5.db";
    private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
    
    private static final String USER_TABLE = "application_table";
    private static final String USER_ID = "ID";
    private static final int NUM_USER_ID = 0;
    private static final String USER_STUD = "Student";
    private static final int NUM_USER_STUD= 1;
    private static final String USER_OFFID = "Offid";
    private static final int NUM_USER_OFFID = 2;
    private static final String USER_CL = "Cover";
    private static final int NUM_USER_CL = 3;
    private static final String USER_COMP = "Company";
    private static final int NUM_USER_COMP = 4;

    private SQLiteDatabase bdd;

    private ApplicationDataBase maBaseSQLite;

    public ApplicationBDD(Context context){
            //On créer la BDD et sa table
            maBaseSQLite = new ApplicationDataBase(context, NOM_BDD, null, VERSION_BDD);
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

    public long insertApplication(Application stud) throws NoSuchAlgorithmException{
            //Création d'un ContentValues (fonctionne comme une HashMap)
            ContentValues values = new ContentValues();
            
            //this.open();
            
            
            
            Application[] myApp = getApplicationByStudent(stud.getStudID());
            
            if(myApp != null)
            {
            	for(int i=0;i<myApp.length;i++)
            	{
            		if(myApp[i].getOfferID() == stud.getOfferID())
            		{
            			return -2;
            		}
            	}
            }
            
            
            //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
            values.put(USER_STUD, stud.getStudID());
            values.put(USER_OFFID, stud.getOfferID());
            values.put(USER_CL, stud.getCoverLetter());
            values.put(USER_COMP, stud.getCompany());
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
    
    public boolean containsApplication(int StudID, int OffID)
    {
    	Application[] myOffFav = getApplicationByStudent(StudID);
        
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

    public int updateApplication(int id, Application stud) throws NoSuchAlgorithmException{
            //Création d'un ContentValues (fonctionne comme une HashMap)
                            ContentValues values = new ContentValues();
                            //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                            values.put(USER_STUD, stud.getStudID());
                            values.put(USER_OFFID, stud.getOfferID());
                            values.put(USER_CL, stud.getCoverLetter());
                            values.put(USER_COMP, stud.getCompany());
                            
                            
            return bdd.update(USER_TABLE, values,USER_ID + " = " +id, null);
    }

    public int removeApplicationByID(int id){
            //Suppression d'un livre de la BDD grâce à l'ID
            return bdd.delete(USER_TABLE, USER_ID + " = " +id, null);
    }

    public Application getApplicationByOffID(int OFFID){
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_STUD,USER_OFFID,USER_CL,USER_COMP},USER_OFFID + " LIKE \"" + OFFID +"\"",null,  null, null, null,null);
            return cursorToApplication(c);
    }
       
    
    public Application[] getApplicationByStudent(int ID){
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_STUD,USER_OFFID,USER_CL,USER_COMP}, USER_STUD + " LIKE \"" + ID +"\"", null, null, null, null,null);
            return cursorsToApplication(c);
    }
    
    public Application[] getApplicationByCompany(String company){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_STUD,USER_OFFID,USER_CL,USER_COMP}, USER_COMP + " LIKE \"" + company +"\"", null, null, null, null,null);
        return cursorsToApplication(c);
}
    

    //Cette méthode permet de convertir un cursor en un livre
    private Application cursorToApplication(Cursor c){
            //si aucun élément n'a été retourné dans la requête, on renvoie null
            if (c.getCount() == 0)
                    return null;

            //Sinon on se place sur le premier élément
            c.moveToFirst();
            //On créé un livre
            Application stud = new Application();
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            stud.setID(c.getInt(NUM_USER_ID));
            stud.setStudID(c.getInt(NUM_USER_STUD));
            stud.setOfferID(c.getInt(NUM_USER_OFFID));
            stud.setCoverLetter(c.getString(NUM_USER_CL));
            stud.setCompany(c.getString(NUM_USER_COMP));
            
            //On ferme le cursor
            c.close();

            //On retourne le livre
            return stud;
    }
    
    public Application[] cursorsToApplication(Cursor c)
	{
    	Application[] liste = new Application[c.getCount()]; 
    	//si aucun élément n'a été retourné dans la requête, on renvoie null 
    	if (c.getCount() == 0) 
    	{ 
    		Application s = new Application(); 
    		Log.e("skat", "Aucune donnée a transtyper"); 
    		return(null); 
    
    	} 
    	else 
    	{ 
    		//Sinon on se place sur le premier élément 
    		c.moveToFirst(); 
    		
    		for (int i = 0; i < c.getCount(); i++)  
    		{ 
    			Application stud = new Application(); 
    			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
    			stud.setID(c.getInt(NUM_USER_ID));
    			stud.setStudID(c.getInt(NUM_USER_STUD));
    			stud.setOfferID(c.getInt(NUM_USER_OFFID));
    			stud.setCoverLetter(c.getString(NUM_USER_CL));
    			stud.setCompany(c.getString(NUM_USER_COMP));
	   
    			liste[i]=stud; 
    			c.moveToNext(); 
    		} 
    		return liste; 
    	} 
 }
}
