package com.example.findmyinternship;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Student_account extends Fragment {
	
	public Student_account() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.student_account_layout, container, false);
        
        return rootView;
    }

}
