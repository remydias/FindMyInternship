package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class StudentBDD {
        private static final int VERSION_BDD = 1;
        private static final String NOM_BDD = "FMI.db";
        private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
        
        private static final String USER_TABLE = "User_table";
        private static final String USER_ID = "ID";
        private static final int NUM_USER_ID = 0;
        private static final String USER_LASTNAME = "Name";
        private static final int NUM_USER_LASTNAME = 1;
        private static final String USER_FIRSTNAME = "Firstname";
        private static final int NUM_USER_FIRSTNAME = 2;
        private static final String USER_MAIL = "Email";
        private static final int NUM_USER_MAIL = 3;
        private static final String USER_School = "School";
        private static final int NUM_USER_School = 4;
        private static final String USER_Pwd = "Password";
        private static final int NUM_USER_Pwd = 5;
 
        private SQLiteDatabase bdd;
 
        private StudentDataBase maBaseSQLite;
 
        public StudentBDD(Context context){
                //On créer la BDD et sa table
                maBaseSQLite = new StudentDataBase(context, NOM_BDD, null, VERSION_BDD);
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
 
        public long insertStudent(Student stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                ContentValues values = new ContentValues();
                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                values.put(USER_LASTNAME, stud.getLastName());
                values.put(USER_FIRSTNAME, stud.getFirstName());
                values.put(USER_MAIL, stud.getEmail());
                values.put(USER_School, stud.getSchool());
                
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(stud.getPassword().getBytes());
                String encryptedString = new String(messageDigest.digest());
                
                values.put(USER_Pwd, encryptedString);
                
                if(this.getStudentBySchool(stud.getSchool()) == null)
                {
                	 return bdd.insert(USER_TABLE, null, values);
                }                
                else if(this.getStudentByName(stud.getLastName()) != null && tabContains(this.getStudentBySchool(stud.getSchool()), stud))
                {
                	return 0;
                }
                else
                	 return bdd.insert(USER_TABLE, null, values);
                //on insère l'objet dans la BDD via le ContentValues
               
        }
        
        public boolean tabContains(Student[] tab, Student s)
        {
        	for(int i=0;i<tab.length;i++)
        	{
        		if(tab[i].getLastName().equals(s.getLastName())&& tab[i].getSchool().equals(s.getSchool()) && tab[i].getFirstName().equals(s.getFirstName()))
        		{
        			return true;
        		}
        	}
        	return false;
        }
 
        public int updateStudent(int id, Student stud) throws NoSuchAlgorithmException{
                //Création d'un ContentValues (fonctionne comme une HashMap)
                                ContentValues values = new ContentValues();
                                //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
                                values.put(USER_LASTNAME, stud.getLastName());
                                values.put(USER_FIRSTNAME, stud.getFirstName());
                                values.put(USER_MAIL, stud.getEmail());
                                values.put(USER_School, stud.getSchool());
                                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                                messageDigest.update(stud.getPassword().getBytes());
                                String encryptedString = new String(messageDigest.digest());
                                
                                values.put(USER_Pwd, encryptedString);
                                
                                
                return bdd.update(USER_TABLE, values,USER_ID + " = " +id, null);
        }
 
        public int removeStudentByID(int id){
                //Suppression d'un livre de la BDD grâce à l'ID
                return bdd.delete(USER_TABLE, USER_ID + " = " +id, null);
        }
 
        public Student getStudentByName(String name){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_School,USER_Pwd}, USER_LASTNAME + " LIKE \"" + name +"\"", null, null, null, null,null);
                return cursorToStudent(c);
        }
        
        public Student getStudentByID(int ID){
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_School,USER_Pwd}, USER_ID + " LIKE \"" + ID +"\"", null, null, null, null,null);
            return cursorToStudent(c);
    }
        
        public Student[] getStudentBySchool(String school){
            //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
            Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_School,USER_Pwd}, USER_School + " LIKE \"" + school +"\"", null, null, null, null,null);
            return cursorsToStudents(c);
    }
        
        public Student getStudentByMail(String mail){
                //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
                Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_School,USER_Pwd}, USER_MAIL + " LIKE \"" + mail +"\"", null, null, null, null,null);
                return cursorToStudent(c);
        }
 
        //Cette méthode permet de convertir un cursor en un livre
        private Student cursorToStudent(Cursor c){
                //si aucun élément n'a été retourné dans la requête, on renvoie null
                if (c.getCount() == 0)
                        return null;
 
                //Sinon on se place sur le premier élément
                c.moveToFirst();
                //On créé un livre
                Student stud = new Student();
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                stud.setID(c.getInt(NUM_USER_ID));
                stud.setLastName(c.getString(NUM_USER_LASTNAME));
                stud.setFirstName(c.getString(NUM_USER_FIRSTNAME));
                stud.setEmail(c.getString(NUM_USER_MAIL));
                stud.setSchool(c.getString(NUM_USER_School));
                stud.setPassword(c.getString(NUM_USER_Pwd));
                //On ferme le cursor
                c.close();
 
                //On retourne le livre
                return stud;
        }
        
        public Student[] cursorsToStudents(Cursor c)
    	{
        	Student[] liste = new Student[c.getCount()]; 
        	//si aucun élément n'a été retourné dans la requête, on renvoie null 
        	if (c.getCount() == 0) 
        	{ 
        		Student s = new Student(); 
        		Log.e("skat", "Aucune donnée a transtyper"); 
        		return(null); 
        
        	} 
        	else 
        	{ 
        		//Sinon on se place sur le premier élément 
        		c.moveToFirst(); 
        		
        		for (int i = 0; i < c.getCount(); i++)  
        		{ 
        			
        			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        			 Student stud = new Student();
                     //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                     stud.setID(c.getInt(NUM_USER_ID));
                     stud.setLastName(c.getString(NUM_USER_LASTNAME));
                     stud.setFirstName(c.getString(NUM_USER_FIRSTNAME));
                     stud.setEmail(c.getString(NUM_USER_MAIL));
                     stud.setSchool(c.getString(NUM_USER_School));
                     stud.setPassword(c.getString(NUM_USER_Pwd));
    	   
        			liste[i]=stud; 
        			c.moveToNext(); 
        		} 
        		return liste; 
        	} 
     }
}