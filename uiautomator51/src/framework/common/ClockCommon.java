package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;

import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import static framework.excute.Excute.excute;

import java.io.IOException;
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
	 * 切换语言
	 * @param language 取值范围：中文 (繁體)，中文 (简体)，English
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException
	 */
	public static void changeLanguage(String language) throws IOException, RemoteException, UiObjectNotFoundException
	{
		DeviceCommon.runADBCommand("am start -a android.settings.LOCALE_SETTINGS");
		excute(Object_Text, Operation_ClickWait, language);
	}
	public static String getTimeZone(String info)
	{
		//System.out.println(info);
		String strReturn= DeviceCommon.extractField(info,"\\d+(?=\\:)");
		//System.out.println("FileTime is"+strReturn);
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
