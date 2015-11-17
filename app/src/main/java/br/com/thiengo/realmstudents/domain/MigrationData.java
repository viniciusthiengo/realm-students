package br.com.thiengo.realmstudents.domain;

import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.internal.ColumnType;
import io.realm.internal.Table;

/**
 * Created by viniciusthiengo on 11/16/15.
 */
public class MigrationData implements RealmMigration {
    public static final int VERSION = 2;

    @Override
    public long execute(Realm realm, long version) {

        if( version == VERSION - 1 ){
            Table studentTable = realm.getTable(Student.class);
            long idKey = studentTable.getColumnIndex("id");
            studentTable.addSearchIndex(idKey);

            Table disciplineTable = realm.getTable(Discipline.class);
            idKey = disciplineTable.getColumnIndex("id");
            disciplineTable.addSearchIndex(idKey);

            Table gradeTable = realm.getTable(Grade.class);
            idKey = gradeTable.getColumnIndex("id");
            gradeTable.addSearchIndex(idKey);

            // REMOVE
                idKey = studentTable.getColumnIndex("age");
                studentTable.removeColumn(idKey);

            Table addressTable = realm.getTable(Address.class);
            addressTable.addColumn( ColumnType.INTEGER, "id", false );
            idKey = addressTable.getColumnIndex("id");
            addressTable.addSearchIndex(idKey);

            long addressStreetKey = addressTable.addColumn(ColumnType.STRING, "street", true);
            long addressNeighborhoodKey = addressTable.addColumn(ColumnType.STRING, "neighborhood", true);
            long addressCityKey = addressTable.addColumn(ColumnType.STRING, "city", true);
            long addressStateKey = addressTable.addColumn(ColumnType.STRING, "state", true);
            long addressCountryKey = addressTable.addColumn(ColumnType.STRING, "country", true);

            long addressKey = studentTable.addColumnLink(ColumnType.LINK, "address", addressTable);
            studentTable.addColumnLink(ColumnType.LINK_LIST, "friends", studentTable);

            for( int i = 0; i < studentTable.size(); i++ ){

                if( studentTable.getString( studentTable.getColumnIndex("name"), i ).equalsIgnoreCase("jake") ){
                    long row = addressTable.addEmptyRow();

                    addressTable.setLong( idKey, row, studentTable.getLong( studentTable.getColumnIndex("id"), i ) );
                    addressTable.setString(addressStreetKey, row, "Rua 1");
                    addressTable.setString(addressNeighborhoodKey, row, "Bairro 1");
                    addressTable.setString(addressCityKey, row, "Cidade 1");
                    addressTable.setString(addressStateKey, row, "Estado 1");
                    addressTable.setString( addressCountryKey, row, "País 1" );

                    studentTable.getUncheckedRow(i).setLink(addressKey, row);
                }
            }
        }

        return ++version;
    }
}
