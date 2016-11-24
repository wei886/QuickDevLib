package NativeDb;

public class QuickBaseTable implements QuickBaseColums {

    public static final String TABLE_NAME_ = "table1";//


    public static final String SQL_CREATE_TABLE_1 =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ + " ( "
                    + _ID + " INTEGER  PRIMARY KEY  AUTOINCREMENT NOT NULL , "
                    + _UID + " INTEGER ) ";

    public static final String SQL_DROP_TABLE_1 = " DROP TABLE IF EXISTS " + TABLE_NAME_;


}
