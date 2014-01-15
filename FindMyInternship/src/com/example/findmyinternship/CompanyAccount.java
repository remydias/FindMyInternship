package com.example.findmyinternship;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompanyAccount extends Fragment{
	public  CompanyAccount() {
        // Empty constructor required for fragment subclasses
    }

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.company_account_layout, container, false);
        
        return rootView;
    }

}
