package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;
import framework.common.DownloadCommon;
import framework.common.FileExplorerCommon;

public class Download extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Download);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * 去手机助手下载APP
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_000() throws UiObjectNotFoundException{
		//主体
		DownloadCommon.downloadAPP(4);
	}
	
	/**
	 * 进入下载界面
	 */
	public static void test_001(){
		//主体
		check(Object_Text,Operation_checkExist,"下载");
	}
	
	/**
	 * 进入下载界面
	 */
	public static void test_102(){
		//前提
		DownloadCommon.SwitchMode("列表视图");
		if(!(Boolean)excute(Object_Text,Operation_Exists,"无任何文件"))
		{
			excute(Object_ResIdInstance, Operation_LongClick, "android:id/title","0");
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_Text,Operation_ClickWait,"全选");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/menu_sort");
		}
		//主体
		excute(Object_Text,Operation_WaitForExists,"无任何文件","5000");
		check(Object_Text,Operation_checkExist,"无任何文件");
	}
	
	/**
	 * 进入下载界面-按名称、按修改时间、按大小 三个功能项
	 */
	public static void test_003(){
		//主体
		DownloadCommon.SwitchMode("列表视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/menu_sort");
		check(Object_Text,Operation_checkExist,"按名称");
		check(Object_Text,Operation_checkExist,"按修改日期");
		check(Object_Text,Operation_checkExist,"按大小");
	}
	
	/**
	 * 进入下载界面-按名称
	 */
	public static void test_004(){
		//主体
		DownloadCommon.SwitchMode("列表视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/menu_sort");
		excute(Object_Text,Operation_ClickWait,"按名称");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "android:id/title", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "android:id/title", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "android:id/title", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedByName(bname, false));
	}
	
	/**
	 * 进入下载界面-按修改时间
	 * @throws ParseException 
	 */
	public static void test_005() throws ParseException{
		//主体
		DownloadCommon.SwitchMode("列表视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/menu_sort");
		excute(Object_Text,Operation_ClickWait,"按修改日期");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.android.documentsui:id/date", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.android.documentsui:id/date", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.android.documentsui:id/date", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(DownloadCommon.isSortedByTime(bname, true));
	}
	
	/**
	 * 进入下载界面-按大小
	 * @throws ParseException 
	 */
	public static void test_006(){
		//主体
		//List<Integer> list=new ArrayList<Integer>();
		DownloadCommon.SwitchMode("列表视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/menu_sort");
		excute(Object_Text,Operation_ClickWait,"按大小");
		String firname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.android.documentsui:id/size", "0");
		String scename = (String)excute(Object_ResIdInstance, Operation_GetText, "com.android.documentsui:id/size", "1");
		String thrname = (String)excute(Object_ResIdInstance, Operation_GetText, "com.android.documentsui:id/size", "2");
		String bname[] = {firname, scename, thrname};
		Assert.assertTrue(FileExplorerCommon.isSortedBySize(bname,true));
	}
	
	/**
	 * 进入网格视图
	 * @throws ParseException 
	 */
	public static void test_007() throws ParseException{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		if(!(Boolean)excute(Object_Text,Operation_Exists,"网格视图")){
			excute(Object_Text,Operation_ClickWait,"列表视图");
			excute(Object_Description,Operation_ClickWait,"更多选项");
		}
		check(Object_Text,Operation_checkExist,"网格视图");
	}
	
	/**
	 * 
	 * @throws ParseException 
	 */
	public static void test_008(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		
	}
	
	/**
	 * 长按一个文件
	 * @throws ParseException 
	 */
	public static void test_009(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.documentsui:id/icon_mime","0");
		check(Object_Text,Operation_checkExist,"已选择 1 项");
	}
	
	/**
	 * 长按一个文件，分享
	 * @throws ParseException 
	 */
	public static void test_010(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.documentsui:id/icon_mime","0");
		excute(Object_ResourceId,Operation_ClickWait, "com.android.documentsui:id/menu_share");
		excute(Object_Text,Operation_WaitForExists,"分享方式","5000");
		check(Object_Text,Operation_checkExist,"电子邮件");
		check(Object_Text,Operation_checkExist,"蓝牙");
	}
	
	/**
	 * 长按一个文件，删除
	 * @throws ParseException 
	 */
	public static void test_011(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.documentsui:id/icon_mime","0");
		excute(Object_ResourceId,Operation_ClickWait, "com.android.documentsui:id/menu_sort");
		check(Object_Text,Operation_checkExist,"删除");
	}
	
	/**
	 * 长按一个文件，更多选项
	 * @throws ParseException 
	 */
	public static void test_012(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.documentsui:id/icon_mime","0");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"全选");
		check(Object_Text,Operation_checkExist,"复制到…");
	}
	
	/**
	 * 长按一个文件，更多选项-全选
	 * @throws ParseException 
	 */
	public static void test_013(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.documentsui:id/icon_mime","0");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全选");
		check(Object_Text,Operation_checkNoExist,"已选择 1 项");
	}
	
	/**
	 * 长按一个文件，更多选项-复制到
	 * @throws ParseException 
	 */
	public static void test_014(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.documentsui:id/icon_mime","0");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"复制到…");
		check(Object_Text,Operation_checkExist,"保存文件");
	}
	
	/**
	 * 长按一个文件，更多选项-安装
	 * @throws ParseException 
	 */
	public static void test_015(){
		//主体
		DownloadCommon.SwitchMode("网格视图");
		excute(Object_ResIdInstance, Operation_ClickWait, "com.android.documentsui:id/icon_mime");
		if((Boolean)excute(Object_Text, Operation_Exists, "禁止安装"))
		{
			check(Object_Text,Operation_checkExist,"禁止安装");
		}else{
			check(Object_Text,Operation_checkExist,"安装");
		}
	}
	
}