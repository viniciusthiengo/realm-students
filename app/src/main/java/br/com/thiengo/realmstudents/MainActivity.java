package br.com.thiengo.realmstudents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

import br.com.thiengo.realmstudents.domain.Address;
import br.com.thiengo.realmstudents.domain.Discipline;
import br.com.thiengo.realmstudents.domain.Student;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button btDisciplines = (Button) findViewById(R.id.bt_disciplines);
        Button btStudents = (Button) findViewById(R.id.bt_students);

        Realm realm = Realm.getDefaultInstance();
        init( realm );

        RealmResults<Discipline> disciplines = realm.where(Discipline.class).findAll();
        RealmResults<Student> students = realm.where(Student.class).findAll();

        btDisciplines.setText( "Disciplinas ("+disciplines.size()+")" );
        btStudents.setText( "Estudantes ("+students.size()+")" );


        Log.i("LOG", "Version: " + realm.getConfiguration().getSchemaVersion());
        realm.close();
    }


    private void init( Realm realm ){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);

        if( sp.getInt("flag", 0) == 0 ){
            Log.i("LOG", "init()");
            sp.edit().putInt("flag", 1).apply();

            try{
                AssetManager assetManager = getAssets();
                InputStream is = null;

                realm.beginTransaction();

                // DISCIPLINES
                    is = assetManager.open("disciplines.json");
                    realm.createOrUpdateAllFromJson(Discipline.class, is);

                // ADDRESS
                    is = assetManager.open("address.json");
                    realm.createAllFromJson( Address.class, is );

                // STUDENTS
                    is = assetManager.open("students.json");
                    realm.createOrUpdateAllFromJson( Student.class, is );

                realm.commitTransaction();

            }
            catch( Exception e ){
                e.printStackTrace();
                realm.cancelTransaction();
            }
        }
        else{
            RealmResults<Student> students = realm.where(Student.class).findAll();
            for( Student s : students ){
                Address a = realm.where(Address.class).equalTo("id", s.getId()).findFirst();
                Log.i("LOG", "Student: "+s.getName());
                Log.i("LOG", "Street: "+a.getStreet());
            }
        }
    }


    // LISTENERS
        public void callDisciplines( View view){
            Intent it = new Intent(this, DisciplinesActivity.class);
            startActivity(it);
        }


        public void callStudents( View view){
            Intent it = new Intent(this, StudentsActivity.class);
            startActivity(it);
        }
}
