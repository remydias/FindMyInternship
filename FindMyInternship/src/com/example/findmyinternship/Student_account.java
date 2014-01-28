package com.example.findmyinternship;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import Model.Student;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Student_account extends Fragment {
	
	int StudID;
	
	public Student_account() {}

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.student_account_layout, container, false);
        
        final LinearLayout mLinearLayout =(LinearLayout)inflater.inflate(R.layout.student_account_layout,container, false);
        
        Bundle b= this.getArguments();
        
        StudID = b.getInt("StudID");
        
        final EditText OLD = (EditText)mLinearLayout.findViewById(R.id.editTextOldPWD);
        final EditText NEW = (EditText)mLinearLayout.findViewById(R.id.editTextNEWPWF);
        final EditText NEWCONF = (EditText)mLinearLayout.findViewById(R.id.editTextNEWPWDCONF);
        
        Button Vall = (Button)mLinearLayout.findViewById(R.id.buttonValModif);
        ImageButton Suppr = (ImageButton)mLinearLayout.findViewById(R.id.imageButtonCROIX);
        
        Vall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String OldPwd = OLD.getText().toString();
		        String NewPwd = NEW.getText().toString();
		        String NewConf = NEWCONF.getText().toString();
		        
		        MainActivity.getStudBdd().open();
		        
		        Student S = MainActivity.getStudBdd().getStudentByID(StudID);
		        
		        MessageDigest messageDigest = null;
				try {
					messageDigest = MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        messageDigest.update(OldPwd.getBytes());
		        String encryptedString = new String(messageDigest.digest());
		        
		        if(encryptedString.equals(S.getPassword()))
		        {
		        	if(NewPwd.equals(NewConf))
		        	{
		        		messageDigest = null;
		        		try {
		        			messageDigest = MessageDigest.getInstance("SHA-256");
		        		} catch (NoSuchAlgorithmException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		                S.setPassword(NewPwd);
		                
		                try {
							MainActivity.getStudBdd().updateStudent(StudID, S);
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		Toast.makeText(getActivity(),"Vérifiez le nouveau PWD", Toast.LENGTH_LONG).show();
		        	}		        	
		        }
		        else
		        {
		        	Toast.makeText(getActivity(),"Ancien PWD HS", Toast.LENGTH_LONG).show();
		        }
		        
		        MainActivity.getStudBdd().close();
		        
		        Intent intent = new Intent(getActivity(), StudentActivity.class);
        		startActivity(intent);
				
			}
		});
        
        Suppr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.getStudBdd().open();
				MainActivity.getStudBdd().removeStudentByID(StudID);
				MainActivity.getStudBdd().close();
				Toast.makeText(getActivity(),"Compte supprimé", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getActivity(), MainActivity.class);
        		startActivity(intent);
			}
		});
        
        
        
        return mLinearLayout;
    }

}
