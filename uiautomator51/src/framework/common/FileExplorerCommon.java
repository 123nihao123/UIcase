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
		excute(Object_Text, Operation_WaitForExists, Name, "10000");
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
		String strNum= DeviceCommon.extractField(strSize,"\\d+(\\.\\d+)?" );
		System.out.println("strNum: " +strNum);
		String strUnit = DeviceCommon.extractField(strSize,"[K|M|G]?B$" );
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
	public static String extractFileType(String info)
	{
		//System.out.println(info);
		String strReturn=DeviceCommon.extractField(info,"(?<=\\.)\\w+");
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
		String strReturn= DeviceCommon.extractField(info,"\\d+(\\.\\d+)?\\s*[K|M|G]?B$");
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
		String strReturn= DeviceCommon.extractField(info,"\\d\\d\\d\\d-\\d\\d-\\d\\d\\s\\d\\d:\\d\\d:\\d\\d");
		//System.out.println("FileTime is"+strReturn);
		return strReturn;
	}

/**
 * 判断是否按文件类型排好序。缺省为升序。
 * @param strArray
 * @return
 */
	public static boolean isSortedByType(String[] strArray)
	{
		return isSortedByType(strArray, false);
	}

/**
 * 判断是否按文件类型排好序。缺省为升序。
 * @param strArray
 * @param isReverse -true:降序排列  false:升序排列，缺省为升序
 * @return
 */
	public static boolean isSortedByType(String[] strArray, boolean isReverse )
	{
		boolean valReturn =true;
		String typeArray[]= new String[strArray.length];

		if(strArray.length==1) return valReturn;

		for(int i=0;i<strArray.length;i++)
		{
			typeArray[i] = extractFileType(strArray[i]);
		}

		/*for(String t : typeArray) {
			System.out.println(t);
		}*/

		if(isReverse)
		{
			//降序
			for(int i=0;i<typeArray.length-1;i++)
			{
				if(typeArray[i].compareTo(typeArray[i+1])<0) //type cann't be chinese so comPareTo is safe
				{
					valReturn = false;
					break;
				}
			}
		}
		else
		{
			//升序
			for(int i=0;i<typeArray.length-1;i++)
			{
				if(typeArray[i].compareTo(typeArray[i+1])>0)
				{
					valReturn = false;
					break;
				}
			}
		}

		return valReturn;
	}

	/**
	 * 判断是否按文件时间排好序。缺省为升序。
	 * @param strArray
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSortedByTime(String[] strArray) throws ParseException
	{
		return isSortedByTime(strArray, false);
	}

	/**
	 * 判断是否按文件时间排好序。缺省为升序。
	 * @param strArray
	 * @param isReverse - true:降序排列  false:升序排列，缺省为升序
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSortedByTime(String[] strArray,boolean isReverse) throws ParseException
	{
		boolean valReturn =true;
		float timeArray[]= new float[strArray.length];

		if (strArray.length==1) return valReturn;

		for(int i=0;i<strArray.length;i++)
		{
			timeArray[i] = stringToTime(extractFileTime(strArray[i]));
		}

		for(float t : timeArray) {
		System.out.println(t);
		}

		if(isReverse)
		{
			//降序
			for(int i=0;i<timeArray.length-1;i++)
			{
				if(timeArray[i]<timeArray[i+1])
				{
					valReturn = false;
					break;
				}
			}
		}
		else
		{
			//升序
			for(int i=0;i<timeArray.length-1;i++)
			{
				if(timeArray[i]>timeArray[i+1])
				{
					valReturn = false;
					break;
				}
			}
		}
		return valReturn;
	}
/**
 * 判断是否按文件大小排好序。缺省为升序。
 * @param strArray
 * @return
 */
	public static boolean isSortedBySize(String[] strArray)
	{
		return isSortedBySize(strArray, false);
	}
/**
 * 判断是否按文件大小排好序。缺省为升序
 * @param strArray
 * @param isReverse - true:降序排列  false:升序排列，缺省为升序
 * @return
 */
	public static boolean isSortedBySize(String[] strArray, boolean isReverse)
	{
		boolean valReturn =true;
		float numArray[]= new float[strArray.length];

		if (strArray.length==1) return valReturn;

		for(int i=0;i<strArray.length;i++)
		{
			numArray[i] = stringToSize(extractFileSize(strArray[i]));
		}

		/*for(float f : numArray) {
			System.out.println(f);
		}*/

		if(isReverse)
		{
			//降序
			for(int i=0;i<numArray.length-1;i++)
			{
				if(numArray[i]<numArray[i+1])
				{
					valReturn = false;
					break;
				}
			}
		}
		else
		{
			//升序
			for(int i=0;i<numArray.length-1;i++)
			{
				if(numArray[i]>numArray[i+1])
				{
					valReturn = false;
					break;
				}
			}
		}
		return valReturn;
	}

	/**
	 * 判断文件名列表是否排好序。缺省为升序。
	 * @param strArray
	 * @return
	 */
	public static boolean isSortedByName(String[] strArray)
	{
		return isSortedByName(strArray, false);
	}

	/**
	 * 判断文件名列表是否排好序
	 * @param fileArray
	 * @param isReverse - true:降序排列  false:升序排列，缺省为升序
	 * @return
	 */
	public static boolean isSortedByName(String[] fileArray, boolean isReverse)
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
	 * 选择存储类型（手机或存储卡）
	 */
	public static void storagePath(String storagetype)
	{
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,storagetype);
	}
	/**
	 * 在存储界面长按某一条目
	 */
	public static void longclickitem(String storagetype)
	{
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,storagetype);
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.fileexplorer:id/file_item_list_name","0");
	}
	
	/**
	 * 在存储界面点击menu
	 */
	public static void clickmenu(String storagetype)
	{
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		excute(Object_Text,Operation_ClickWait,storagetype);
		excute(Object_Device, Operation_PressMenu);
	}
	/**
	 * 清除搜索界面的文件类型选项
	 */
	public static void ClearSearchType()
	{
		String [] str3={"com.sprd.fileexplorer:id/fragment_search_type_image",
		                "com.sprd.fileexplorer:id/fragment_search_type_vedio",
		                "com.sprd.fileexplorer:id/fragment_search_type_audio",
		                "com.sprd.fileexplorer:id/fragment_search_type_document",
		                "com.sprd.fileexplorer:id/fragment_search_type_apks",
		                "com.sprd.fileexplorer:id/fragment_search_type_other"};
		for(int i=0;i<6;i++)
		{
			if((Boolean)excute(Object_ResourceId, Operation_IsChecked, str3[i]))
	         {
			    excute(Object_ResourceId, Operation_ClickWait, str3[i]);
		     }
        }
	}
	
	
	/**
	 * 预览文件。缺省点击第一个文件。按文件点击时，需要输入文件名
	 * @param appName -打开文件app,取值范围：音乐，视频播放器
	 */
	public static void viewfile(String appName)
	{
		viewfile(appName, "");
	}
	
	/**
	 * 预览文件。缺省点击第一个文件。按文件点击时，需要输入文件名
	 * @param storagetype - 取值范围：快速查看，手机，存储卡
	 * @param appName -打开文件app,取值范围：音乐，视频播放器,HTML查看器等。
	 * @param play
	 * @param fileName - 
	 */
	public static void viewfile(String appName, String fileName)
	{
		
		if(fileName.equals(""))
		{
			//点击第一个
			excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.fileexplorer:id/file_item_list_name", "0");
		}
		else
		{
			//按文件名点击
			excute(Object_Text, Operation_ClickWait, fileName);
		}
		
		if ((Boolean)excute(Object_Text, Operation_Exists, "打开方式"))
		{	
			//首次打开
			excute(Object_Text, Operation_ClickWait, appName);
			excute(Object_Text, Operation_ClickWait, "仅此一次");
		}
		else if((Boolean)excute(Object_ResourceId, Operation_Exists, "android:id/contentPanel"))
		{
			excute(Object_Text, Operation_ClickWait, "仅此一次");
		}
	}
	
	
	
}
