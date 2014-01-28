package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class OfferBDD {
	
        private static final int VERSION_BDD = 1;
        private static final String NOM_BDD = "FMI3.db";
        private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
        
        private static final String USER_TABLE = "Offer_table";
        private static final String USER_ID = "ID";
        private static final int NUM_USER_ID = 0;
        private static final String USER_COMP = "Company";
        private static final int NUM_USER_COMP= 1;
        private static final String USER_TITLE = "Title";
        private static final int NUM_USER_TITLE = 2;
        private static final String USER_DESC = "Description";
        private static final int NUM_USER_DESC = 3;
        private static final String USER_SKILLS = "Skills";
        private static final int NUM_USER_SKILLS = 4;
        private static final String USER_PROFILE = "Profile";
        private static final int NUM_USER_PROFILE = 5;
        private static final String USER_DURATION = "Duration";
        private static final int NUM_USER_DURATION = 6;
        private static final String USER_SALARY = "Salary";
        private static final int NUM_USER_SALARY = 7;
 
        private SQLiteDatabase bdd;
 
        private OfferDataBase maBaseSQLite;
 
        public OfferBDD(Context context){
                //On créer la BDD et sa table
                maBaseSQLite = new OfferDataBase(context, NOM_BDD, null, VERSION_BDD);
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
 
        public long insertOffer(Offer stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                ContentValues values = new ContentValues();
                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                values.put(USER_COMP, stud.getCompany());
                values.put(USER_TITLE, stud.getTitle());
                values.put(USER_DESC, stud.getDescription());
                values.put(USER_SKILLS, stud.getCompetences());
                values.put(USER_PROFILE, stud.getProfil());
                
                values.put(USER_DURATION, stud.getDuration());
                values.put(USER_SALARY, stud.getSalary());
                //on insère l'objet dans la BDD via le ContentValues
                return bdd.insert(USER_TABLE, null, values);
        }
 
        public int updateOffer(int id, Offer stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                                ContentValues values = new ContentValues();
                                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                                values.put(USER_COMP, stud.getCompany());
                                values.put(USER_TITLE,stud.getTitle());
                                values.put(USER_DESC,stud.getDescription());
                                values.put(USER_SKILLS,stud.getCompetences());
                                values.put(USER_PROFILE, stud.getProfil());
                                
                                values.put(USER_DURATION, stud.getDuration());
                                values.put(USER_SALARY, stud.getSalary());
                                
                                
                return bdd.update(USER_TABLE, values,USER_ID + " = " +id, null);
        }
 
        public int removeOfferByID(int id){
                //Suppression d'un livre de la BDD grâce à l'ID
                return bdd.delete(USER_TABLE, USER_ID + " = " +id, null);
        }
 
        public Offer getOfferByName(String name){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_COMP,USER_TITLE, USER_DESC, USER_SKILLS, USER_PROFILE,USER_DURATION,USER_SALARY}, USER_TITLE + " LIKE \"" + name +"\"", null, null, null, null,null);
                return cursorToOffer(c);
        }
        
        public Offer getOfferByID(int ID){
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_COMP,USER_TITLE, USER_DESC, USER_SKILLS, USER_PROFILE,USER_DURATION,USER_SALARY}, USER_ID + " LIKE \"" + ID +"\"", null, null, null, null,null);
            return cursorToOffer(c);
    }
        
        
        
        public Offer[] getOfferByComp(String mail){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_COMP,USER_TITLE, USER_DESC, USER_SKILLS, USER_PROFILE,USER_DURATION,USER_SALARY}, USER_COMP + " LIKE \"" + mail +"\"", null, null, null, null,null);
                return cursorsToOffers(c);
        }
        
        public Offer[] getAllOffers(){
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_COMP,USER_TITLE, USER_DESC, USER_SKILLS, USER_PROFILE,USER_DURATION,USER_SALARY}, null, null, null, null, null,null);
            return cursorsToOffers(c);
    }
 
        //Cette méthode permet de convertir un cursor en un livre
        private Offer cursorToOffer(Cursor c){
                //si aucun élément n'a été retourné dans la requête, on renvoie null
                if (c.getCount() == 0)
                        return null;
 
                //Sinon on se place sur le premier élément
                c.moveToFirst();
                //On créé un livre
                Offer stud = new Offer();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                stud.setID(c.getInt(NUM_USER_ID));
                stud.setCompany(c.getString(NUM_USER_COMP));
                stud.setTitle(c.getString(NUM_USER_TITLE));
                stud.setDescription(c.getString(NUM_USER_DESC));
                stud.setCompetences(c.getString(NUM_USER_SKILLS));
                stud.setProfil(c.getString(NUM_USER_PROFILE));
                stud.setDuration(c.getString(NUM_USER_DURATION));
                stud.setSalary(c.getString(NUM_USER_SALARY));
                //On ferme le cursor
                c.close();
 
                //On retourne le livre
                return stud;
        }
        
        public Offer[] cursorsToOffers(Cursor c)
    	{
        	Offer[] liste = new Offer[c.getCount()]; 
        	//si aucun élément n'a été retourné dans la requête, on renvoie null 
        	if (c.getCount() == 0) 
        	{ 
        		Offer s = new Offer(); 
        		Log.e("skat", "Aucune donnée a transtyper"); 
        		return(null); 
        
        	} 
        	else 
        	{ 
        		//Sinon on se place sur le premier élément 
        		c.moveToFirst(); 
        		
        		for (int i = 0; i < c.getCount(); i++)  
        		{ 
        			Offer stud = new Offer(); 
        			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        			stud.setID(c.getInt(NUM_USER_ID));
        			stud.setCompany(c.getString(NUM_USER_COMP));
        			stud.setTitle(c.getString(NUM_USER_TITLE));
        			stud.setDescription(c.getString(NUM_USER_DESC));
        			stud.setCompetences(c.getString(NUM_USER_SKILLS));
        			stud.setProfil(c.getString(NUM_USER_PROFILE));
        			stud.setDuration(c.getString(NUM_USER_DURATION));
        			stud.setSalary(c.getString(NUM_USER_SALARY));
    	   
        			liste[i]=stud; 
        			c.moveToNext(); 
        		} 
        		return liste; 
        	} 
     }
}