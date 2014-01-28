package com.example.findmyinternship;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

public class StudentProfile extends Fragment {
	
	private static int RESULT_LOAD_IMAGE = 1;
	private String student_name;
	private String student_pers;
	
	public StudentProfile() {
    }
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
        
        
        ScrollView mLinearLayout =(ScrollView)inflater.inflate(R.layout.student_profile,container, false);
        
        ImageButton buttonImg = (ImageButton)mLinearLayout.findViewById(R.id.Student_photo);
        
        
        //Récupération du nom de la compagnie appelante
        TextView company = (TextView)mLinearLayout.findViewById(R.id.StudentName);
        TextView comppers = (TextView)mLinearLayout.findViewById(R.id.Student_person);
       // TextView companypers = (TextView)mLinearLayout.findViewById(R.id.company_person);
        Bundle b= this.getArguments();
       
       // if(company_name.length()<30)
        student_name = b.getString("name");
        student_pers=b.getString("cperson");
       // else
        	//company_name = b.getString("name").substring(0, 30);
        company.setText(student_name);
        comppers.setText(student_pers);
       /* if(company_pers.length()<40)
        	company_pers=b.getString("cperson");
        else
        	company_pers=b.getString("cperson").substring(0, 40);
        companypers.setText(company_pers);
        */
        
  
        buttonImg.setOnClickListener(new OnClickListener() {
			
        	  @Override
        	  public void onClick(View v) {
        		  
        		  Intent i = new Intent(
                  Intent.ACTION_PICK,
                  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                   
                  startActivityForResult(i, RESULT_LOAD_IMAGE);
        		}
        	});
        
        return mLinearLayout;
    }
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
             
            ImageButton buttonImg = (ImageButton)getActivity().findViewById(R.id.Student_photo);
            
            buttonImg.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(picturePath),180,170));
         
        }
     
     
    }
	
	/************************ Calculations for Image Sizing *********************************/ 
	public Drawable ResizeImage (int imageID) { 
	//Get device dimensions 
	Display display = getActivity().getWindowManager().getDefaultDisplay(); 
	double deviceWidth = display.getWidth(); 

	BitmapDrawable bd=(BitmapDrawable) this.getResources().getDrawable(imageID); 
	double imageHeight = bd.getBitmap().getHeight(); 
	double imageWidth = bd.getBitmap().getWidth(); 

	double ratio = deviceWidth / imageWidth; 
	int newImageHeight = (int) (imageHeight * ratio); 

	Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageID); 
	Drawable drawable = new BitmapDrawable(this.getResources(),getResizedBitmap(bMap,newImageHeight,(int) deviceWidth)); 

	return drawable; 
	} 

	/************************ Resize Bitmap *********************************/ 
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) { 

	int width = bm.getWidth(); 
	int height = bm.getHeight(); 

	float scaleWidth = ((float) newWidth) / width; 
	float scaleHeight = ((float) newHeight) / height; 

	// create a matrix for the manipulation 
	Matrix matrix = new Matrix(); 

	// resize the bit map 
	matrix.postScale(scaleWidth, scaleHeight); 

	// recreate the new Bitmap 
	Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false); 

	return resizedBitmap; 
	}
}
