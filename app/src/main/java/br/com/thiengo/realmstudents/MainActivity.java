package br.com.thiengo.realmstudents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        RealmResults<Discipline> disciplines = realm.where(Discipline.class).findAll();
        RealmResults<Student> students = realm.where(Student.class).findAll();

        btDisciplines.setText( "Disciplinas ("+disciplines.size()+")" );
        btStudents.setText( "Estudantes ("+students.size()+")" );


        Log.i("LOG", "Version: " + realm.getConfiguration().getSchemaVersion());
        realm.close();
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
