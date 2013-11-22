package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
 
	private SQLiteDatabase bdd;
 
	private StudentDataBase maBaseSQLite;
 
	public StudentBDD(Context context){
		//On cr�er la BDD et sa table
		maBaseSQLite = new StudentDataBase(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		//on ouvre la BDD en �criture
		bdd = maBaseSQLite.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'acc�s � la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertStudent(Student stud){
		//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(USER_LASTNAME, stud.getLastName());
		values.put(USER_FIRSTNAME, stud.getFirstName());
		values.put(USER_MAIL, stud.getEmail());
		values.put(USER_School, stud.getSchool());
		//on ins�re l'objet dans la BDD via le ContentValues
		return bdd.insert(USER_TABLE, null, values);
	}
 
	public int updateStudent(int id, Student stud){
		//Cr�ation d'un ContentValues (fonctionne comme une HashMap)
				ContentValues values = new ContentValues();
				//on lui ajoute une valeur associ� � une cl� (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
				values.put(USER_LASTNAME, stud.getLastName());
				values.put(USER_FIRSTNAME, stud.getFirstName());
				values.put(USER_MAIL, stud.getEmail());
				values.put(USER_School, stud.getSchool());
		return bdd.update(USER_TABLE, values,USER_ID + " = " +id, null);
	}
 
	public int removeStudentByID(int id){
		//Suppression d'un livre de la BDD gr�ce � l'ID
		return bdd.delete(USER_TABLE, USER_ID + " = " +id, null);
	}
 
	public Student getStudentByName(String name){
		//R�cup�re dans un Cursor les valeur correspondant � un livre contenu dans la BDD (ici on s�lectionne le livre gr�ce � son titre)
		Cursor c = bdd.query(USER_TABLE, new String[] {USER_ID, USER_LASTNAME, USER_FIRSTNAME, USER_MAIL, USER_School}, USER_LASTNAME + " LIKE \"" + name +"\"", null, null, null, null);
		return cursorToStudent(c);
	}
 
	//Cette m�thode permet de convertir un cursor en un livre
	private Student cursorToStudent(Cursor c){
		//si aucun �l�ment n'a �t� retourn� dans la requ�te, on renvoie null
		if (c.getCount() == 0)
			return null;
 
		//Sinon on se place sur le premier �l�ment
		c.moveToFirst();
		//On cr�� un livre
		Student stud = new Student();
		//on lui affecte toutes les infos gr�ce aux infos contenues dans le Cursor
		stud.setID(c.getInt(NUM_USER_ID));
		stud.setLastName(c.getString(NUM_USER_LASTNAME));
		stud.setFirstName(c.getString(NUM_USER_FIRSTNAME));
		stud.setEmail(c.getString(NUM_USER_MAIL));
		stud.setSchool(c.getString(NUM_USER_School));
		//On ferme le cursor
		c.close();
 
		//On retourne le livre
		return stud;
	}
}
