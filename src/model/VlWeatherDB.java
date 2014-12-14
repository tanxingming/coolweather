package model;

import java.util.ArrayList;
import java.util.List;

import db.VlWeatherOpenHelper;
import android.R.color;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class VlWeatherDB {
public static final String DB_NAME="vl_weather";
public static final int VERSION=1;
private static VlWeatherDB vlweatherDB;
private SQLiteDatabase db;

private VlWeatherDB(Context context) {
	VlWeatherOpenHelper dbHelper=new VlWeatherOpenHelper(context, DB_NAME, null, VERSION);
	db=dbHelper.getWritableDatabase();
}
public synchronized static VlWeatherDB getInstance(Context context){
	if(vlweatherDB==null){
	
		vlweatherDB =new VlWeatherDB(context);
	}
	return vlweatherDB;
}    //单列模式，保证只有一个实例， 而不被销毁！


/**
 *   save(保持)    将实例的属性取出来保持到数据库去
 * */
public void saveProvince(Province province){   
	if(province!=null){
		ContentValues values=new ContentValues(); //new 一个容器来装 province的数据
		values.put("province_name", province.getProvinceName());
		values.put("province_code", province.getProvinceCode());
		db.insert("Province", null, values);
	}
}

/**
 *   从数据库读取全国所有省份信息添加到Province实例
 * */
 public List<Province> loadProvinces(){
	 List<Province> list =new ArrayList<Province>();
	 Cursor cursor=db.query("Province", null, null, null, null, null, null);
	 if(cursor.moveToFirst()){
		 do{ 
			 Province province=new Province();
			 province.setId(cursor.getColumnIndex("id"));
		     province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
	         province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
		     list.add(province);
		 }while(cursor.moveToNext());}
	 
	return list;
	 }
 //把实例数据存储到数据库
    public void saveCity(City city){
    	if(city!=null){
    		ContentValues values=new ContentValues();
    		values.put("city_name", city.getCityName());
    		values.put("city_code", city.getCityCode());
    		values.put("province_id", city.getProvinceId());
    		db.insert("City", null, values);
    	}
    }
    
  public List<City> loadCities(int provinceId){
	  List<City> list=new ArrayList<City>();
	  Cursor cursor=db.query("City", null, "province_id=?", new String[] {String.valueOf(provinceId)}, null, null, null);
	  if(cursor.moveToFirst()){
		  do
		  { City city = new City();
		    city.setId(cursor.getInt(cursor.getColumnIndex("id")));
		    city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
		    city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
		    list.add(city);
		  }while(cursor.moveToNext());}
	return list;
	  
  }
 
 public void saveCounty(County county){
	 if(county!=null){
		 ContentValues values =new ContentValues();
		 values.put("county_name", county.getCountyName());
		 values.put("county_code", county.getCountyCode());
		 values.put("city_id", county.getCityId());
		 db.insert("County", null, values);
	 }
 }
 
 public List<County> loadCounties(int cityId){
	 List<County> list =new ArrayList<County>();
	 Cursor cursor=db.query("County", null, "city_id=?",new String[]{String.valueOf(cityId)}, null, null, null);
	  if(cursor.moveToFirst()){
		  do{
			      County county =new County();
			  county.setId(cursor.getInt(cursor.getColumnIndex("id")));
			  county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
			  county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
		      list.add(county);
		  } while(cursor.moveToNext());
			  
	  }
	 
	 return list;
	 
 } 
}
