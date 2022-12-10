package com.example.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        int drinkId=getIntent().getIntExtra(EXTRA_DRINKID,0);

        SQLiteOpenHelper starbuzzDatabaseHelper=new StarbuzzDatabaseHelper(this);
        try(SQLiteDatabase db=starbuzzDatabaseHelper.getReadableDatabase()){
            Cursor cursor=db.query("DRINK",
                    new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE"},
                    "_id=?",
                    new String[] {Integer.toString(drinkId)},
                    null,null,null);
            if(cursor.moveToFirst()){//导航至游标第一条记录
                String nameText=cursor.getString(0);
                String descriptionText=cursor.getString(1);
                int photoid=cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);
                TextView name=findViewById(R.id.name);
                name.setText(nameText);
                TextView description=findViewById(R.id.description);
                description.setText(descriptionText);
                ImageView photo=findViewById(R.id.photo);
                photo.setImageResource(photoid);
                photo.setContentDescription(nameText);
            }
            cursor.close();
        }catch (SQLiteException e){
            Log.e("sqlite",e.getMessage());
            Toast toast= Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
        CheckBox favorite = (CheckBox)view;
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE",favorite.isChecked());

        SQLiteOpenHelper starbuzzDatabaseHelper=new StarbuzzDatabaseHelper(this);

        int drinkId=getIntent().getIntExtra(EXTRA_DRINKID,0);
        try(SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase()){
            int row = db.update("DRINK",drinkValues,"_id=?",new String[]{Integer.toString(drinkId)});
            Log.d("sqlite","update row"+row);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }
}