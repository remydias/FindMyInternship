package com.example.findmyinternship;

import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StudentHelp extends Fragment {
	
	public StudentHelp() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.student_help, container, false);
        
        return rootView;
    }

}
