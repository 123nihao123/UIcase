package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.data.DeviceParameter.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Rect;
import android.os.RemoteException;
import static framework.excute.Excute.*;

public class DeviceCommon 
{
	public static String sim1Num, sim2Num;
	public static String simFlag; //"00" for no sim, "10" for sim1, "01" for sim2, "11" for sim1&sim2
	
	static{
		try {
			initialSIM();
		} catch (RemoteException | UiObjectNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void unLock()
	{
		excute(Object_Device, Operation_WakeUp);
		if((Boolean) excute(Object_ResourceId, Operation_Exists, Devices_ResId_unlock))
		{
			Rect bounds = (Rect) excute(Object_ResourceId, Operation_GetBounds, Devices_ResId_unlock);
			String x = Integer.toString(bounds.centerX());
			String y = Integer.toString(bounds.centerY());
			excute(Object_Device, Operation_DiviceDrag, x, y, x, "0", "10");	
		}
	}
	
	public static void clearBackGround()
	{
		excute(Object_Device, Operation_PressRecentApps);		
		Wait(4000);
		if((Boolean) excute(Object_ResourceId, Operation_Exists, Devices_ResId_ClearButton))
		{
			excute(Object_ResourceId, Operation_ClickWait, Devices_ResId_ClearButton);
		}
		else 
		{
			while ((Boolean) excute(Object_ResourceId, Operation_Exists, Devices_ResId_TaskView)) 
			{
				excute(Object_ResourceId, Operation_ClickWait, Devices_ResId_DismissTask);
			}
		}
	}
	
	 public static void enterApp(String appName) throws UiObjectNotFoundException
	 {
	 System.out.println("======Start to excute DeviceCommon: enterApp======");
	 excute(Object_Device, Operation_PressHome);
	 if(!(Boolean) excute(Object_Description, Operation_Exists, appName))
	 {
	 excute(Object_Description, Operation_ClickWait, Devices_Desc_Applycation);
	 UiScrollable applists = new UiScrollable(new UiSelector().scrollable(true));
	         while(!(Boolean) excute(Object_Description, Operation_Exists, appName)){
	         applists.scrollTextIntoView(appName);
	 }
	 }
	 excute(Object_Description, Operation_ClickWait, appName);
	 }
	
	public static void exitApp() throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute DeviceCommon: exitApp======");
		while(!(Boolean) excute(Object_Description,Operation_Exists,"应用"))
		{
			excute(Object_Device, Operation_PressBack);
		}
	}
	
	public static void swipe(String direction,int step,int loop) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute DeviceCommon: swipe======");
		int x = UiDevice.getInstance().getDisplayWidth();
		int y = UiDevice.getInstance().getDisplayHeight();
		while(loop > 0)
		{
			if(direction.equals("Left"))
				UiDevice.getInstance().swipe(x/2, y/2,0,y/2,step);
			else if(direction.equals("Right"))
				UiDevice.getInstance().swipe(x/2, y/2,x,y/2,step);
			else if(direction.equals("Up"))
				UiDevice.getInstance().swipe(x/2, y/2,x/2,0,step);
			else if(direction.equals("Down"))
				UiDevice.getInstance().swipe(x/2, y/2,x/2,y,step);
			loop--;
		}
	}
//	
//	public static void exitApp(String appName) throws UiObjectNotFoundException
//	{
//		
//	}
//	
//	public static void pressKeySerial(String keySerial) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute DeviceCommon: pressKeySerial======");
//		
//		for (int i = 0; i < keySerial.length(); i++) 
//		{
//			char  item =  keySerial.charAt(i);
//			new PressKeyCode(String.valueOf(item)).action();
//        }
//	}
//	
//	public static void pressKeySerialAsNumber(String key, int number) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute DeviceCommon: pressKeySerialAsNumber======");
//		
//		for (int i = 0; i < number; i++) 
//		{
//			pressKeySerial(key);
//        }
//	}
//	
//	public static void pressDirectionSerialAsNumber(String direction, int number) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute DeviceCommon: pressDirectionSerialAsNumber======");
//		
//		for (int i = 0; i < number; i++) 
//		{
//			new PressDirection(direction).action();
//        }
//	}
//	
	public static void isTextExistAndClick(String text) throws UiObjectNotFoundException
	{
		if((Boolean) excute(Object_Text,Operation_Exists,text))
		{
			excute(Object_Text,Operation_ClickWait,text);	
		}
	}
	
	public static void isDescExistAndClick(String text) throws UiObjectNotFoundException
	{
		if((Boolean) excute(Object_Text,Operation_Exists,text))
		{
			excute(Object_Description,Operation_ClickWait,text);	
		}
	}
//	
//	public static void isResIdExistAndClick(String text) throws UiObjectNotFoundException
//	{
//		if(new GetObjectStatusByResId(text).getObjectStatus("isExist"))
//		{
//			new FindAppByResIdAndClick(text).action();	
//		}
//	}
//	
	public static void selectMore(String name) throws UiObjectNotFoundException
	{
		String[] nameList = name.split(",");
		for(int i = 0; i < nameList.length; i++)
		{
			excute(Object_TextScroll,Operation_ClickWait,nameList[i]);
		}
	}
	
	 public static void closeSwitch(int num) throws UiObjectNotFoundException{
	        for(int i=0; i<num; i++){
	            UiObject index = (UiObject)excute(Object_ResIdInstance, Operate_ReturnObject, "com.android.packageinstaller:id/switchWidget",String.valueOf(i));
	            if(index.getText().equals("OFF")){
	                index.clickAndWaitForNewWindow();
	            }
	        }
	    }
	 
	 public static void removePermissions() throws UiObjectNotFoundException, RemoteException
	 {
		 	excute(Object_TextScroll, Operation_ClickWait, "应用", "vertical");
	        String[] appList = {"信息","浏览器","相机","文件管理器","电话","通讯录","日历","电子邮件","Launcher3","HTML 查看程序","文档", "音乐", "小区广播","图库"};
	        
	        for(int i=0; i<appList.length; i++){
	        	excute(Object_Device, Operation_PressMenu);
		        if((Boolean)excute(Object_Text, Operation_Exists, "显示系统进程"))
		        {
		        	excute(Object_Text, Operation_ClickWait, "显示系统进程");
		        }
	            excute(Object_TextScroll, Operation_ClickWait, appList[i], "vertical");
	            excute(Object_Text, Operation_ClickWait, "权限");
	            int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.android.packageinstaller:id/list"))).intValue();
	            if((Boolean)excute(Object_Text, Operation_Exists, "其他权限")){
	                Num = Num-1;
	                closeSwitch(Num);
	                excute(Object_ResIdText, Operation_ClickWait, "android:id/title","其他权限");
	                int secondNum = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "com.android.packageinstaller:id/list"))).intValue();
	                closeSwitch(secondNum);        
	            }
	            else{
	            	
	            closeSwitch(Num);
	            }
	    		DeviceCommon.enterApp(Devices_Desc_Setting);
	            excute(Object_TextScroll, Operation_ClickWait, "应用", "vertical");
	        }
	    }

	
 public static void waittingFor(UiObject uiobject,int N) throws UiObjectNotFoundException {
	int i = 1;
	while(!uiobject.exists()){
		Wait(1000);
		System.out.println("Waiting for...");
		if(i>N){
			System.out.println("Timeout...but I still can't find her...");
			break;
		}
		i++;
	}
}
//
//等待一段时间，但不进行点击操作，直到uiobject消失
   public static void waitUntilDisapper(UiObject uiobject,int N) throws UiObjectNotFoundException {
	   int i = 1;
	   while(uiobject.exists()){
		  Wait(1000);
		  System.out.println("Waiting Disapper...");
		   if(i>N){
			System.out.println("Timeout...but She still did not leave...");
			break;
		}
		i++;
	}
}
//寻找桌面小部件并执行相应操作
	public static void lookForWidgetByObject(UiObject object, String text) throws UiObjectNotFoundException 
	{
		int i=1;
		UiObject uiscrollable = (UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.launcher3:id/workspace");
		while(!(Boolean) excute(Object_ClassDesc,Operation_Exists,"android.widget.TextView","应用"))
		{
			excute(Object_Device, Operation_PressBack);
		}
		excute(Object_Device, Operation_PressHome);
		
		while(!object.exists())
		{
			if(i == 1)
			{
				UiDevice.getInstance().pressHome();//按home键进入第一屏（前提是在退出所有应用到IDEL界面）
				Wait(1000);
			}
			uiscrollable.swipeLeft(10);
			if(i>5)
			{
				break;
			}
			i++;
		}
		
		if(text.equals("Click"))
		{
			object.clickAndWaitForNewWindow();
		}
		else if(text.equals("Delete"))
		{
			Rect data = (Rect) excute(Object_ResourceId, Operation_GetBounds,"com.android.quicksearchbox:id/search_widget_text");
			
			int x = data.centerX();
			int y = data.centerY();
			object.dragTo(x, y, 50);
		}
	}

	public static UiObject ScrollIntoView(UiObject object) throws UiObjectNotFoundException
	{
		return ScrollIntoView(object, "vertical");
	}
	
	public static UiObject ScrollIntoView(UiObject object, String scrollDirection) throws UiObjectNotFoundException 
	{
		UiScrollable applist = new UiScrollable(new UiSelector().scrollable(true));
		if(scrollDirection == "horizontal")
			applist.setAsHorizontalList();
		
		if(applist.scrollIntoView(object))
		{
			return object;
		}
		else
		{
			Assert.assertTrue("Error: the needed object can't find!!!",false);
			return null;
		}
	}
	
	public static String runADBCommand(String adbCommand) throws IOException 
	{ 
	    String returnValue = "", line; 
	    InputStream inStream = null; 
	    try { 
	        String command[]= {"/bin/sh","-c",adbCommand};
	        Process process = Runtime.getRuntime().exec(command);
	        inStream = process.getInputStream(); 
	        BufferedReader brCleanUp = new BufferedReader( 
	        new InputStreamReader(inStream)); 
	        while ((line = brCleanUp.readLine()) != null) { 
	        returnValue = returnValue + line + "\n"; 
	    } 
	        brCleanUp.close(); 
	    try { 
	        process.waitFor(); 
	    } catch (InterruptedException e) { 
	        e.printStackTrace(); 
	    } 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	       System.err.println("Error: " + e.getMessage()); 
	    } 
	//System.out.println(returnValue); 
	        return returnValue; 
	}
	
	public static void searchFile(String path, String filename) 
	{
		String returnValue = ""; 
		//adb; 
		try{ 
		    returnValue = DeviceCommon.runADBCommand("find "+path+" -name "+filename); 
		}catch (IOException e) 
		{ 
		    e.printStackTrace(); 
		} 
		System.out.println("The return Value is:" + returnValue); 
		String regEx = "contacts.vcf"; 
		Pattern pattern = Pattern.compile(regEx); 
		Matcher matcher = pattern.matcher(returnValue); 
		if(!matcher.find()) 
		{ 
		    Assert.assertTrue("Error: contacts.vcf not found!!!",false); 
		} 
		else 
		{ 
		    System.out.println("contacts.vcf is found:"); 
		}
	}
	
	public static void deleteFile(String path, String filename) 
	{
		String returnValue = ""; 
		//adb; 
		try{ 
		    returnValue = DeviceCommon.runADBCommand("rm -f "+path+"/"+filename); 
		}catch (IOException e) 
		{ 
		    e.printStackTrace(); 
		}
		System.out.println("The return Value is:" + returnValue); 
	}
	
	/**
	 * 打开数据库。注意数据库所有操作结束后要调用closeDatabase（）关闭数据库。
	 * @param dbPath
	 * @return
	 */
	public static SQLiteDatabase openDatabase(String dbPath)
	{ 
		SQLiteDatabase database = openDatabase(dbPath,SQLiteDatabase.OPEN_READWRITE);
		return database;
	}


	/**
	 * 打开数据库。注意数据库所有操作结束后要调用closeDatabase（）关闭数据库。
	 * @param dbPath
	 * @param flag - to control database access mode. OPEN_READWRITE, OPEN_READONLY, CREATE_IF_NECESSARY, and/or NO_LOCALIZED_COLLATORS.
	 * @return
	 */
	public static SQLiteDatabase openDatabase(String dbPath, int flag)
	{
		SQLiteDatabase database = SQLiteDatabase.openDatabase(dbPath, null, flag);
		return database;
	}
	
	/**
	 * 关闭数据库
	 * @param db
	 */
	public static void closeDatabase(SQLiteDatabase db)
	{
		if( db != null )
		{
			db.close();
		}
	}
	
	/**
	 * 插入一条记录到数据库。注意数据库所有操作结束后要调用closeDatabase（）关闭数据库。
	 * @param db
	 * @param tableName
	 * @param fieldList
	 * @param valueList
	 */
	public static void insertToDatabase(SQLiteDatabase db,String tableName, String fieldList, String valueList)
	{ 
		//String sql = "INSERT INTO calls (number,date,duration,type) VALUES ('3331476008',1456560265000,120,3)";
		String sql= "INSERT INTO " + tableName +" ("+fieldList+") VALUES ("+ valueList+")";
		System.out.println("SQL is: "+ sql);
		db.execSQL(sql);
	}
	
	/**
	 * 获取记录总数。注意数据库所有操作结束后要调用closeDatabase（）关闭数据库。
	 * @param db
	 * @param tableName
	 * @return
	 */
	public static int getRecordCountInDatabase(SQLiteDatabase db,String tableName)
	{ 
		//String sql ="SELECT * FROM calls";
		String sql ="SELECT * FROM "+tableName;
		System.out.println("SQL is: "+ sql);
		Cursor cursor = db.rawQuery(sql, null);
		int total = cursor.getCount();
		cursor.close();
		return total;
		
	}
	/**
	 * 删除所有记录
	 * @param db
	 * @param tableName
	 */
	public static void deleteAllFromDatabase(SQLiteDatabase db,String tableName)
	{ 
		//String sql ="DELETE * FROM calls";
		String sql ="DELETE FROM "+tableName;
		System.out.println("SQL is: "+ sql);
		db.execSQL(sql);		
	}
	/**
	 * 获得SIM ICC_ID
	 * @param simSlot - "SIM1" for slot1, "SIM2" for slot2
	 * @return
	 */
	public static String getSIMID(String simSlot)
	{
		//SIM_SLOT
		String dbPath = "/data/data/com.android.providers.telephony/databases/telephony.db";
		SQLiteDatabase database = openDatabase(dbPath,SQLiteDatabase.OPEN_READONLY);
		String iccID=null;
		String id="";
		if(simSlot=="SIM1")
		{
			id ="0";
		}
		else if(simSlot=="SIM2")
		{
			id="1";
		}
		else
		{
			Assert.assertTrue("SIM slot error",false);
		}
		//System.out.println("simSlot is: "+ simSlot );
		Cursor cursor = database.query("siminfo",new String[]{"icc_id"},"sim_id=?",new String[]{id},null,null,null,null);
		while (cursor.moveToNext()){
			iccID = cursor.getString(0);
			System.out.println(simSlot + " iccID is: "+ iccID );
		}
		cursor.close();
		return iccID;
	}
	/**
	 * 得到某个目录下文件数目
	 * @param folder
	 * @return
	 */
	public static int getFileCount(String folder)
	{
		return getFileCount(folder,"");
	}

	/**
	 * 得到某个目录下文件数目
	 * @param folder - 目录路径
	 * @param type - 文件类型，比如“jpg","gif"
	 * @return
	 */
	public static int getFileCount(String folder, String type )
	{
		String adbCommand;
		String cmdResult="";
		int returnValue=0;

		if(type.equals(""))
		{
			//adbCommand = "ls /sdcard/DCIM/Camera |busybox wc -l";
			adbCommand = "ls " + folder + " |busybox wc -l";
		}
		else
		{
			//adbCommand = "ls /sdcard/DCIM/Camera |busybox grep " + type +" |busybox wc -l";
			adbCommand = "ls "+folder+ " |busybox grep " + type +" |busybox wc -l";
		}
		try{
			cmdResult= runADBCommand(adbCommand);
			//System.out.println("Command is : "+ adbCommand);
			//System.out.println("cmdResult is : "+ cmdResult);
			String s[] = cmdResult.split("\n");
			returnValue = Integer.parseInt(s[0]);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return returnValue ;
	}
	
	public static String parseTagFromXml(String path,String tag)
	{
		File file=  new File(path);
		BufferedInputStream str = null;
		Map map = null;
		String v=null;
		//System.out.println("+++"+file.exists());
		if (file.exists() && file.canRead())
		{
			 try{
				 str = new BufferedInputStream(new FileInputStream(file), 16*1024);
				 map = XmlUtils.readMapXml(str);
				 v = (String)map.get(tag);
				 //System.out.println("The value is "+ v);
			 }
			 catch (Exception e) {
				 e.printStackTrace(); 
				 System.err.println("Error: " + e.getMessage());
			 }
		}
		else
		{
			Assert.assertTrue("Errror: Can not read file!!!",false);
		}
		return v;
	}
	/**
	 * 获取所有sim卡的信息，以Map的方式返回
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> GetSIMInfo() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		String path = "data/local/tmp/PhoneNumList.xml";
		String SIMNum = null, SIMiccID = null;
		String [] simID = {"SIM1", "SIM2"};
		for(int i=0;i<simID.length;i++){
			SIMiccID = DeviceCommon.getSIMID(simID[i]);
			if(SIMiccID != null){
				SIMNum = DeviceCommon.parseTagFromXml(path, SIMiccID);
				Assert.assertTrue("Can't find sim Info(" + simID[i] + " iccID:" + SIMiccID + ") in xml, please add!!!", SIMNum != null);
				map.put(simID[i], SIMNum);
			}
		}
		return map;
	}
	/**
	 * 单独获取simNum的接口
	 * @throws IOException
	 */
	public static String GetSIMNum(String simID) throws IOException {
		String path = "data/local/tmp/PhoneNumList.xml";
		String SIMNum = null;
		String SIMiccID = DeviceCommon.getSIMID(simID);
		if(SIMiccID != null){
			SIMNum = DeviceCommon.parseTagFromXml(path, SIMiccID);
			Assert.assertTrue("Can't find sim Info(" + simID + " iccID:" + SIMiccID + ") in xml, please add!!!", SIMNum != null);
		}
		return SIMNum;
	}
	/**
	 * 获取手机SN号
	 * @return
	 */
	public static String getSerialNumber() {
		String serial = null;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class);
			serial = (String) get.invoke(c, "ro.serialno");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serial;
	}
	/**
	 * 初始化SIM卡信息，得到sim1Num、sim2Num、simFlag
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException
	 */
	public static void initialSIM() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		String [] simId = {"SIM1", "SIM2"};
		Map<String, String> map = DeviceCommon.GetSIMInfo();
		switch(map.size()){
		case 0:
			simFlag = "00";
			break;
		case 1:
			Set<String> simInfo = map.keySet();
			Object[] deviceArrary = simInfo.toArray();
			String sim = deviceArrary[0].toString();
			if(sim.equals(simId[0])){
				sim1Num = (String)map.get(simId[0]);
				simFlag = "10";
			}
			else if(sim.equals(simId[1])){
				sim2Num = (String)map.get(simId[1]);
				simFlag = "01";
			}
			break;
		case 2:
			sim1Num = (String)map.get(simId[0]);
			sim2Num = (String)map.get(simId[1]);
			simFlag = "11";
			break;
		default:
			Assert.assertTrue("None sim status !!!",false);
			break;
		}
	}
	public static String extractField(String info, String ptn)
	{
		String strReturn="";
		Pattern p = Pattern.compile(ptn);
		Matcher m = p.matcher(info);
		while (m.find())
		{
			strReturn = m.group();
			//System.out.println("in while strReturn: " +strReturn);
		}
		return strReturn;
	}
}