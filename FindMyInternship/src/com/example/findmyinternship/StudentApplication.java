package com.example.findmyinternship;

import java.security.NoSuchAlgorithmException;

import Model.Application;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StudentApplication extends Activity{
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_application);
		
		b=getIntent().getExtras();
		String offer_title = b.getString("titree");
		String description = b.getString("desc");
		
		final TextView TVTitre = (TextView)findViewById(R.id.TV_Apply_Title);
		TVTitre.setText(offer_title);
		final TextView TVDesc = (TextView)findViewById(R.id.TV_Apply_desc);
		TVDesc.setText(description);
		
        final EditText EDCL = (EditText)findViewById(R.id.editTextCoverLetter);
        final ImageButton valide = (ImageButton)findViewById(R.id.imageButtonValApply);
        
        valide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Application app = new Application();
				app.setOfferID(b.getInt("id"));
				app.setStudID(b.getInt("StudID"));
				app.setCoverLetter(EDCL.getText().toString());
				app.setCompany(b.getString("company"));
				
				MainActivity.getAppBdd().open();
				try {
					if(MainActivity.getAppBdd().insertApplication(app)!= -2)
					{
						Toast.makeText(StudentApplication.this, "Candidature envoyée", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(StudentApplication.this, "Candidature existe déjà", Toast.LENGTH_LONG).show();
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainActivity.getAppBdd().close();
				
				
				
			}
		});
        
        
		
		
	}

}
