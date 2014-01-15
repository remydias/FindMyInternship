package com.example.findmyinternship;

import java.util.Locale;


import com.example.findmyinternship.R;
import com.example.findmyinternship.R.layout;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Profile extends Fragment{
	
	public Profile() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.profile_layout, container, false);
        
        return rootView;
    }
	
}
