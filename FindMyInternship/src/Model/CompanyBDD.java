package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CompanyBDD {
	
        private static final int VERSION_BDD = 1;
        private static final String NOM_BDD = "FMI2.db";
        private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
        
        private static final String USER_TABLE = "Company_table";
        private static final String USER_ID = "ID";
        private static final int NUM_USER_ID = 0;
        private static final String USER_LASTNAME = "Name";
        private static final int NUM_USER_LASTNAME = 1;
        private static final String USER_FIRSTNAME = "Firstname";
        private static final int NUM_USER_FIRSTNAME = 2;
        private static final String USER_MAIL = "Email";
        private static final int NUM_USER_MAIL = 3;
        private static final String USER_Company = "Company";
        private static final int NUM_USER_Company = 4;
        private static final String USER_Pwd = "Password";
        private static final int NUM_USER_Pwd = 5;
        private static final String USER_DESC = "Description";
        private static final int NUM_USER_DESC = 6;
 
        private SQLiteDatabase bdd;
 
        private CompanyDataBase maBaseSQLite;
 
        public CompanyBDD(Context context){
                //On créer la BDD et sa table
                maBaseSQLite = new CompanyDataBase(context, NOM_BDD, null, VERSION_BDD);
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
 
        public long insertCompany(Company stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                ContentValues values = new ContentValues();
                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                values.put(USER_LASTNAME, stud.getLastName());
                values.put(USER_FIRSTNAME, stud.getFirstName());
                values.put(USER_MAIL, stud.getEmail());
                values.put(USER_Company, stud.getSchool());
                
                
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(stud.getPassword().getBytes());
                String encryptedString = new String(messageDigest.digest());
                
                values.put(USER_Pwd, encryptedString);
                values.put(USER_DESC, stud.getDescription());
                //on insère l'objet dans la BDD via le ContentValues
                return bdd.insert(USER_TABLE, null, values);
        }
 
        public int updateCompany(int id, Company stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                                ContentValues values = new ContentValues();
                                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                                values.put(USER_LASTNAME, stud.getLastName());
                                values.put(USER_FIRSTNAME, stud.getFirstName());
                                values.put(USER_MAIL, stud.getEmail());
                                values.put(USER_Company, stud.getSchool());
                                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                                messageDigest.update(stud.getPassword().getBytes());
                                String encryptedString = new String(messageDigest.digest());
                                
                                values.put(USER_Pwd, encryptedString);
                                values.put(USER_DESC, stud.getDescription());
                                
                                
                return bdd.update(USER_TABLE, values,USER_ID + " = " +id, null);
        }
 
        public int removeCompanyByID(int id){
                //Suppression d'un livre de la BDD grâce à l'ID
                return bdd.delete(USER_TABLE, USER_ID + " = " +id, null);
        }
 
        public Company getCompanyByName(String name){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_Company,USER_Pwd,USER_DESC}, USER_LASTNAME + " LIKE \"" + name +"\"", null, null, null, null,null);
                return cursorToCompany(c);
        }
        
        public Company getCompanyByMail(String mail){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_Company,USER_Pwd,USER_DESC}, USER_MAIL + " LIKE \"" + mail +"\"", null, null, null, null,null);
                return cursorToCompany(c);
        }
 
        //Cette méthode permet de convertir un cursor en un livre
        private Company cursorToCompany(Cursor c){
                //si aucun élément n'a été retourné dans la requête, on renvoie null
                if (c.getCount() == 0)
                        return null;
 
                //Sinon on se place sur le premier élément
                c.moveToFirst();
                //On créé un livre
                Company stud = new Company();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                stud.setID(c.getInt(NUM_USER_ID));
                stud.setLastName(c.getString(NUM_USER_LASTNAME));
                stud.setFirstName(c.getString(NUM_USER_FIRSTNAME));
                stud.setEmail(c.getString(NUM_USER_MAIL));
                stud.setSchool(c.getString(NUM_USER_Company));
                stud.setPassword(c.getString(NUM_USER_Pwd));
                stud.setDescription(c.getString(NUM_USER_DESC));
                //On ferme le cursor
                c.close();
 
                //On retourne le livre
                return stud;
        }
}