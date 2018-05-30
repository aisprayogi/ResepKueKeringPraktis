package reseplengkap.kue.kering.submit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import reseplengkap.kue.kering.R;

/*
 *Created By steemit.com/@iqbalhood
 *30 May 2018
 */

public class SubmitRecipeActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mMessageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_recipe);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Submit Your Cookie Recipe");
        }


        Button btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRecipe();

            }
        });
    }


    //storing token to firebase server
    public void submitRecipe() {

        EditText edEmail = (EditText) findViewById(R.id.edEmail);
        EditText edName = (EditText) findViewById(R.id.edName);
        EditText edRecipeName = (EditText) findViewById(R.id.edRecipeName);
        EditText edRecipeImage = (EditText) findViewById(R.id.edRecipeImage);
        EditText edHowToCook = (EditText) findViewById(R.id.edHowToCook);
        EditText edIngredient = (EditText) findViewById(R.id.edIngredient);

        final String email = edEmail.getText().toString();
        final String name = edName.getText().toString();
        final String recipe_name = edRecipeName.getText().toString();
        final String recipe_image = edRecipeImage.getText().toString();
        final String recipe_ingredient = edHowToCook.getText().toString();
        final String recipe_how_to_cook = edIngredient.getText().toString();


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mMessageReference = FirebaseDatabase.getInstance().getReference("recipe");
        String key = mDatabase.child("recipe").push().getKey();
        mMessageReference.child(key).child("email").setValue(email);
        mMessageReference.child(key).child("name").setValue(name);
        mMessageReference.child(key).child("recipe_name").setValue(recipe_name);
        mMessageReference.child(key).child("recipe_image").setValue(recipe_image);
        mMessageReference.child(key).child("recipe_ingredient").setValue(recipe_ingredient);
        mMessageReference.child(key).child("recipe_how_to_cook").setValue(recipe_how_to_cook);


        //display toast after insert data
        Toast.makeText(getApplicationContext(), "Your Recipe Has Been Submitted..!!!", Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }


}


