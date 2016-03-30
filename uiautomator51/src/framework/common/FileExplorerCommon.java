package framework.common;


import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

//import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 长按并进入选项
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
	 * 由格式如"1970-01-06 11:45:55"的字符串获得时间
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
 * 由格式如“3.66MB”的字符串计算文件长度，单位转换成B
 * @param strSize - 由格式如“3.66MB”的字符串
 * @return - 返回浮点数
 */
	public static float stringToSize(String strSize)
	{
		String strNum= extractField(strSize,"\\d+(\\.\\d+)?" );
		System.out.println("strNum: " +strNum);
		String strUnit = extractField(strSize,"[K|M|G]?B$" );
		System.out.println("strUnit: " +strUnit);
		float num = Float.parseFloat(strNum);
		if(strUnit.equals("B"))
		{
			return num;
		}
		else if(strUnit.equals("KB"))
		{
			num=num*1024;
		}
		else if (strUnit.equals("MB"))
		{
			num=num*1024*1024;
		}
		else if (strUnit.equals("GB"))
		{
			num=num*1024*1024*1024;
		}
		return num;
	}
	public static String extractField(String info, String ptn)
	{
		String strReturn="";
		Pattern p = Pattern.compile(ptn);
		Matcher m = p.matcher(info);
		while (m.find())
		{
			strReturn = m.group();
			//System.out.println("strReturn: " +strReturn);
		}
		return strReturn;
	}
	/**
	 * 提取信息中的文件大小字符串
	 * @param info -输入字符串格式为“日期:2016-03-28 09:30:41  大小:3.66MB”
	 * @return - 返回字符串，比如“3.66MB”
	 */
	public static String extractFileSize(String info)
	{
		//System.out.println(info);
		String strReturn= extractField(info,"\\d+(\\.\\d+)?[K|M|G]?B$");
		return strReturn;
	}

	/**
	 * 提取信息中的文件时间字符串
	 * @param info - 输入字符串格式为“日期:2016-03-28 09:30:41  大小:3.66MB”
	 * @return -返回字符串，比如“2016-03-28 09:30:41”
	 */
	public static String extractFileTime(String info)
	{
		//System.out.println(info);
		String strReturn= extractField(info,"\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d:\\d\\d:\\d\\d");
		//System.out.println("FileTime is"+strReturn);
		return strReturn;
	}
	/**
	 * 判断文件名列表是否排好序。缺省为升序。
	 * @param strArray
	 * @return
	 */
	public static boolean isFileNameSorted(String[] strArray)
	{
		return isFileNameSorted(strArray, false);
	}

	/**
	 * 判断文件名列表是否排好序
	 * @param fileArray
	 * @param isReverse - true:降序排列  false:升序排列，缺省为升序
	 * @return
	 */
	public static boolean isFileNameSorted(String[] fileArray, boolean isReverse)
	{
		boolean returnValue = true;

		List<String> fileList = new ArrayList<String>();
		Collections.addAll(fileList, fileArray);

		Collections.shuffle(fileList); //shuffle the file name to make sure re-sort will happen

		String[] newFileArray = new String[fileList.size()];
		fileList.toArray(newFileArray); //back to array in order to re-sort 

		Comparator c = Collator.getInstance(Locale.CHINA);
		Arrays.sort(newFileArray,c);

		if (isReverse) Collections.reverse(Arrays.asList(newFileArray));

		/*for(String str : newFileArray) {
			System.out.println(str);
		}*/
		for(int i=0;i<fileArray.length;i++)
		{
			if (!fileArray[i].equals(newFileArray[i]))
			{
				returnValue= false;
				break;
			}

		}
		return returnValue;
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
