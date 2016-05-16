package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.Devices_Desc_Browser;
import static framework.excute.Excute.*;

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

import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;



public class DownloadCommon {
	
	/**
	 * 切换视图：网格视图，列表视图
	 */
	public static void SwitchMode(String type){
		excute(Object_Description,Operation_ClickWait,"更多选项");
		String txt = (String)excute(Object_ResourceId,Operation_GetText,"android:id/title");
		if(type.equals(txt)){
			excute(Object_ResourceId,Operation_ClickWait,"android:id/title");
		}
		else{
			excute(Object_Device, Operation_PressBack);
		}
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
		String strNum= DeviceCommon.extractField(strSize,"\\d\\d+(\\.\\d+)?" );
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
		String strReturn= DeviceCommon.extractField(info,"\\d\\d+(\\.\\d\\d+)?[K|M|G]?B$");
		//String strReturn= DeviceCommon.extractField(info,"\\d{1}+(\\.\\d\\d+)?[K|M|G]?B$");
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
	 * 下载App从百度手机卫士
	 * @param num
	 * @throws UiObjectNotFoundException 
	 */
	public static void downloadAPP(int num) throws UiObjectNotFoundException{
		for(int i=0;i<num;i++){
		DeviceCommon.enterApp(Devices_Desc_Browser);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","shouji.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度手机助手 最具人气的应用商店","100000");
		Rect download = (Rect) excute(Object_DescInstance,Operation_GetBounds,"下载",String.valueOf(num));
		int x = download.centerX();
		int y = download.bottom;
		UiDevice.getInstance().click(x, y);
		excute(Object_ResourceId,Operation_WaitForExists,"android:id/alertTitle","100000");
		excute(Object_Text,Operation_ClickWait,"下载");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/tabs","100000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/closetab");
		}
	}
	

}