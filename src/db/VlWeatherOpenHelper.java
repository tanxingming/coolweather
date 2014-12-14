package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;



public class VlWeatherOpenHelper extends SQLiteOpenHelper {
	
public static final String PROVINCE="Province";
public static final String CTIY="Ctiy";
public static final String COUNTY="County";
public static final String CREATE_PROVINCE="create table if not exists Province("+
"id integer primary key autoincrement,"
+"province_name text, "
+"province_code text )";     // 新建省表

public static final String CREATE_CITY="create table if not exists City ( "
+" id integer primary key autoincrement, "
+" city_name text,"
+" city_code text,"
+"province_id integer)";   // 新建市表

public static final String CREATE_COUNTY="create table if not exists County ("
+" id integer primary key autoincrement,"
+" county_name text,"
+" county_code text,"
+" city_id integer)";
	
	
	
	
	
	public VlWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	
	}//在model包下的VlWeatherDB类中会传数据给这个方法
	 





	@Override
	public void onCreate(SQLiteDatabase db) {
	  db.execSQL(CREATE_PROVINCE);
	  db.execSQL(CREATE_CITY);
	  db.execSQL(CREATE_COUNTY);
		
	}





	//用这个方法可以在删除某数据库前备份  某数据库
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXISTS "+PROVINCE); //备份该表
		db.execSQL("DROP TABLE IF EXISTS "+CTIY);//备份该表
		db.execSQL("DROP TABLE IF EXISTS "+COUNTY);  //备份该表
        onCreate(db);   
	}

	
}
