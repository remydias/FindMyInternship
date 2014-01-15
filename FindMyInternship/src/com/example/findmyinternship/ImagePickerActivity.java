package com.example.findmyinternship;

import java.io.FileNotFoundException;
import java.io.InputStream;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
 
public class ImagePickerActivity extends Activity {
 
    private final int SELECT_PHOTO = 1;
    private ImageView imageView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_profile);
        
        ImageButton buttonImg = (ImageButton)findViewById(R.id.Company_photo);
 
 
        buttonImg.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View view) {               
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
 
        switch(requestCode) {
        case SELECT_PHOTO:
            if(resultCode == RESULT_OK){
                try {
                    final Uri imageUri = imageReturnedIntent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
 
            }
        }
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }   
}
