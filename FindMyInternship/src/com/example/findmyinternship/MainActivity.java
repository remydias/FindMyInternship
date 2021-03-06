package com.example.findmyinternship;

import Model.ApplicationBDD;
import Model.CompanyBDD;
import Model.OfferBDD;
import Model.OfferFavoriteBDD;
import Model.Student;
import Model.StudentBDD;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	
	private static final int CODE_DE_MON_ACTIVITE = 1;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public static StudentBDD studBdd; 
    public static CompanyBDD compBdd; 
    public static OfferBDD offBdd;
    public static OfferFavoriteBDD offFavBdd; 
    public static ApplicationBDD appBdd; 

	public static ApplicationBDD getAppBdd() {
		return appBdd;
	}

	public static void setAppBdd(ApplicationBDD appBdd) {
		MainActivity.appBdd = appBdd;
	}

	public static OfferFavoriteBDD getOffFavBdd() {
		return offFavBdd;
	}

	public static void setOffFavBdd(OfferFavoriteBDD offFavBdd) {
		MainActivity.offFavBdd = offFavBdd;
	}

	public static CompanyBDD getCompBdd() {
		return compBdd;
	}

	public static OfferBDD getOffBdd() {
		return offBdd;
	}

	public static void setOffBdd(OfferBDD offBdd) {
		MainActivity.offBdd = offBdd;
	}

	public static void setCompBdd(CompanyBDD compBdd) {
		MainActivity.compBdd = compBdd;
	}

	public static StudentBDD getStudBdd() {
		return studBdd;
	}

	public static void setStudBdd(StudentBDD studBdd) {
		MainActivity.studBdd = studBdd;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView TV1 = (TextView)findViewById(R.id.test);
		
		studBdd= new StudentBDD(this);
		compBdd= new CompanyBDD(this);
		offBdd= new OfferBDD(this);
		offFavBdd= new OfferFavoriteBDD(this);
		appBdd=new ApplicationBDD(this);
 
		//on r�cup�re tous les �l�ments
        ImageButton buttonOui = (ImageButton)findViewById(R.id.buttonValidRegister);
        ImageButton buttonNon = (ImageButton)findViewById(R.id.buttonCancelRegister);
        
      
        
        buttonOui.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this, StudentActivity.class);
        		startActivity(intent);
        		}
        	});
        
        buttonNon.setOnClickListener(new OnClickListener() {
			
      	  @Override
      	  public void onClick(View v) {
      		Intent intent = new Intent(MainActivity.this, CompanyActivity.class);
      		startActivity(intent);
      		}
      	});
   
	}

}

