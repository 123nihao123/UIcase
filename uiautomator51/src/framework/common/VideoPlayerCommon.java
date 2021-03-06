package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.check;
import static framework.excute.Excute.excute;

import java.lang.reflect.Array;

import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;

public class VideoPlayerCommon 
{
    /**
	 * 将所有视频删除
	 */
	public static void deleteAllVideo()
    {
		 if((Boolean)excute(Object_Text,Operation_Exists,"相机")){
             return;
         }
    	UiDevice.getInstance().pressMenu();
    	excute(Object_Text, Operation_ClickWait,"选择视频");
    	if ((Boolean) excute(Object_Text, Operation_Exists,"选中了 0 项"))
    	{
    		excute(Object_Text, Operation_ClickWait,"选中了 0 项");
    		excute(Object_Text, Operation_ClickWait,"全选");
    		if((Boolean) excute(Object_Description, Operation_Exists,"删除"))
    		{
    			excute(Object_Description, Operation_ClickWait,"删除");
        		excute(Object_Text, Operation_ClickWait,"确定");
    		}
    		else
    		{
    			excute(Object_Description, Operation_ClickWait,"更多选项");
        		excute(Object_Text, Operation_ClickWait,"删除");
        		excute(Object_Text, Operation_ClickWait,"确定");
    		}
    	}
    }
	 /**
	 * 通过相机添加视频
	 */
	public static void addVideo(int num)
	{
		excute(Object_Description,Operation_ClickWait,"切换到相机");
		for(int i=0;i<num;i++)
		{
			excute(Object_Description,Operation_WaitForExists,"快门","5000");
			excute(Object_Description,Operation_ClickWait,"快门");
			Wait(20000);
			excute(Object_Description,Operation_ClickWait,"快门");
		}	
		excute(Object_Device,Operation_PressBack);
	}
	/**
	 * 点击一个视频
	 */
	public static void clickVideo()
	{
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.gallery3d:id/gl_root_view", "10000");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		if(!(boolean) excute(Object_Description, Operation_Exists,"播放视频"))
		{
			UiDevice.getInstance().click(x, y);
			Wait(1000);
		}
	}
   /**
    * 点击页面上的一个群组
    */
	public static void clickVideoGroup()
	{
		UiDevice.getInstance().pressMenu();
    	excute(Object_Text, Operation_ClickWait,"选择视频");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);		
	}
	/**
	 * 选择分享视频群组的方式
	 */
	public static void shareVideoGroupBy(String name)
	{
		excute(Object_Description,Operation_WaitForExists,"分享方式","5000");
		String text="使用"+name+"分享";
		if((Boolean)excute(Object_Description,Operation_Exists,text))
			excute(Object_Description,Operation_ClickWait,text);
		else
		{
			excute(Object_Description,Operation_ClickWait,"分享方式");
			excute(Object_Text, Operation_ClickWait,name);
		}
	}
	/**
	 * 播放界面菜单
	 * @param menulist 菜单列表：更多选项，分享方式
	 * @param menu
	 */
	public static void Playscreenmenu(String menulist, String menu)
	{
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.gallery3d:id/gl_root_view", "10000");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		if(!(boolean) excute(Object_Description, Operation_Exists,"播放视频"))
		{
			UiDevice.getInstance().click(x, y);
			Wait(1000);
		}
		excute(Object_Description, Operation_ClickWait, menulist);
		if((boolean) excute(Object_Text, Operation_Exists, menu))
		{
			excute(Object_Text, Operation_ClickWait, menu);
		}	
	}
	/**
	 * 播放界面分享菜单
	 * @param menu：蓝牙，电子邮件，信息
	 */
	public static void Playscreenshare(String menu)
	{
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.gallery3d:id/gl_root_view", "10000");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		if(!(boolean) excute(Object_Description, Operation_Exists,"播放视频"))
		{
			UiDevice.getInstance().click(x, y);
			Wait(1000);
		}
		excute(Object_Description, Operation_ClickWait, "分享方式");
		if((boolean) excute(Object_Text, Operation_Exists, menu))
		{
			excute(Object_Text, Operation_ClickWait, menu);
		}else{
			excute(Object_Device, Operation_PressBack);
			excute(Object_ResourceId, Operation_ClickWait, "android:id/default_activity_button");
		}
			
	}

	/**
	 * 切换模式：幻灯片视图，网格视图
	 * @param mode
	 */
	public static void switchMode(String mode)
	{
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.gallery3d:id/gl_root_view", "10000");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		Wait(1000);
		excute(Object_ResourceId,Operation_WaitForExists,"android:id/action_bar_title","5000");
		excute(Object_Device, Operation_PressBack);
		if(!(Boolean)excute(Object_Text,Operation_Exists,mode))
		{
			excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
			excute(Object_Text,Operation_ClickWait,mode);
		}
	}
	
	/**
	 * 进入一个视频文件里（不播放）
	 */
	public static void comeToVideoScreen()
	{
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.gallery3d:id/gl_root_view", "10000");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		Wait(1000);
	}
	
	/**
	 * 分享方式：当选中一个分享方式后，则下拉列表里不会再出现该方式
	 * @param type
	 */
	public static void shareType(String type)
	{
		excute(Object_Description, Operation_ClickWait, "分享方式");
		if((boolean) excute(Object_Text, Operation_Exists, type))
		{
			excute(Object_Text, Operation_ClickWait, type);
		}else{
			excute(Object_Device, Operation_PressBack);
			excute(Object_ResourceId, Operation_ClickWait, "android:id/default_activity_button");
		}
	}
	/**
	 * 分享模式
	 */
	public static void checksharemenu()
	{
		String type[] = {"信息", "蓝牙", "电子邮件"};
		if((boolean) excute(Object_ResourceId, Operation_Exists, "android:id/default_activity_button"))
		{
			excute(Object_Description, Operation_ClickWait, "分享方式");
			String text1 = (String) excute(Object_ResIdInstance, Operation_GetText, "android:id/list_item", "0");
			String text2 = (String) excute(Object_ResIdInstance, Operation_GetText, "android:id/list_item", "1");
			for (int i=0; i<3; i++)
			{
				if(text1.equals(type[i]))
					check(Object_Text, Operation_checkExist, type[i]);
				if(text2.equals(type[i]))
					check(Object_Text, Operation_checkExist, type[i]);
			}
		}else{
			excute(Object_Description, Operation_ClickWait, "分享方式");
			check(Object_Text, Operation_checkExist, "信息");
			check(Object_Text, Operation_checkExist, "蓝牙");
			check(Object_Text, Operation_checkExist, "电子邮件");
		}	
		
	}
	
	/**
	 * 网格视图中选择条目
	 */
	public static void chooseMenu()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择条目");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
	}
}
