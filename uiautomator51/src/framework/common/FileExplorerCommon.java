package framework.common;


import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

//import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.imageio.ImageIO;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.driver.AndroidObject;
public class FileExplorerCommon 
{
	/**
	 * 进入分类
	 */
	public static void Enterclass(String ClassName)
	{
		excute(Object_Text, Operation_ClickWait, ClassName);
		excute(Object_Text, Operation_WaitForExists, ClassName, "10000");
	}
	/**
	 * 进入分类菜单
	 */
	public static void classmenu(String ClassName)
	{
		excute(Object_Text, Operation_ClickWait, ClassName);
		excute(Object_Text, Operation_WaitForExists, ClassName, "10000");
		excute(Object_Device, Operation_PressMenu);
	}
	/**
	 * 长按选项
	 */
	public static void Longclickmenu(String Name)
	{
		excute(Object_ResourceId, Operation_LongClick, "com.sprd.fileexplorer:id/file_item_list_name");
		excute(Object_Text, Operation_ClickWait, Name);
	}
	/**
	 * 选择多个
	 */
	public static void select(String ClassName)
	{
		excute(Object_Text, Operation_ClickWait, ClassName);
		excute(Object_Text, Operation_WaitForExists, ClassName, "10000");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "选择多个");
		excute(Object_Text, Operation_WaitForExists, "选择全部", "10000");
		excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.fileexplorer:id/select_checkbox", "0");
	}
	/**
	 * 菜单
	 */
	public static void menu(String menuName)
	{
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, menuName);
	}
	
	/**
	 * 
	 * @param strTime - 格式如"1970-01-06 11:45:55"
	 * @return
	 * @throws ParseException
	 */
	public static long stringToTime(String strTime) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
	    Date date = format.parse(strTime);  
	    //System.out.print("Format To times:"+date.getTime());  
	    return date.getTime();
	}
	/**
	 * 到存储卡界面
	 */
	public static void cometoSD()
	{
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,"存储卡");
	}
	/**
	 * 在存储卡界面长按某一条目
	 */
	public static void longclickiteminSD()
	{
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,"存储卡");
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.fileexplorer:id/file_item_list_name","0");
	}
	
	/**
	 * 在SD卡存储界面点击menu
	 */
	public static void clickmenuinSD()
	{
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,"存储卡");
		excute(Object_Device, Operation_PressMenu);
	}

}
