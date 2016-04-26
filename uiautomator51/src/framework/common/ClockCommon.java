package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;

import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import static framework.excute.Excute.excute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class ClockCommon
{
	/**
	 * 切换模式
	 * @param name 取值范围：闹钟，时钟，计时器，秒表
	 */
	public static void switchMode(String name)
	{
		excute(Object_Description,Operation_ClickWait,name);
	}
	/**
	 * 进入一个闹钟的展开设置界面
	 */
	public static void enterAlarmSettings()
	{
		if (!(Boolean)excute(Object_Description,Operation_Exists,"添加闹钟"))
			ClockCommon.switchMode("闹钟");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.deskclock:id/arrow","0");
	}
	/**
	 * 进入设置重复时间界面
	 */
	public static void enterRepeatSettings()
	{
		ClockCommon.enterAlarmSettings();
		excute(Object_Text,Operation_ClickWait,"重复");
        excute(Object_Text,Operation_ClickWait,"重复");
	}

	/**
	 * 中文切英文
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException 
	 */
	public static void changetoEnglish() throws UiObjectNotFoundException, RemoteException
	{
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "语言和输入法", "vertical");
		excute(Object_Text, Operation_ClickWait, "语言");
		excute(Object_Text, Operation_ClickWait, "English");
		ClearBackgroundApp();
	}
	/**
	 * 中文切英文
	 * @throws UiObjectNotFoundException
	 */
	public static void changetoChinese() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		excute(Object_Description, Operation_ClickWait, "Apps");
		excute(Object_Description, Operation_ClickWait, "Settings");
        excute(Object_TextScroll, Operation_ClickWait, "Language & input", "vertical");
        excute(Object_Text, Operation_ClickWait, "Language");
		excute(Object_Text, Operation_ClickWait, "中文 (简体)");
	}
	public static String extractFileTime(String info)
	{
		//System.out.println(info);
		String strReturn= extractField(info,"-\\d\\d");
		//System.out.println("FileTime is"+strReturn);
		return strReturn;
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
	/**
	 * 菜单按钮
	 * @param menu
	 */
	public static void cityMenu(String menu)
	{
		excute(Object_Description, Operation_ClickWait, "更多选项");
		if((boolean) excute(Object_Text, Operation_Exists, menu))
		{
			excute(Object_Text, Operation_ClickWait, menu);
		}else{
			excute(Object_Device, Operation_PressBack);
		}
			
	}
	/**
	 * 设置选项
	 * @param option 取值范围：setting中的菜单
	 */
	public static void settingMenu(String option)
	{
		cityMenu("设置");
		excute(Object_TextScroll, Operation_ClickWait, option, "vertical");	
	}
}
