package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.excute;

import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;

public class VideoPlayerCommon 
{
    /**
	 * 将所有视频删除
	 */
	public static void deleteAllVideo()
    {
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
		for(int i=0;i<num;i++)
		{
			excute(Object_Description,Operation_ClickWait,"切换到相机");
			excute(Object_Description,Operation_WaitForExists,"快门","5000");
			excute(Object_Description,Operation_ClickWait,"快门");
			Wait(5000);
			excute(Object_Description,Operation_ClickWait,"快门");
			excute(Object_Device,Operation_PressBack);
		}		
	}
	/**
	 * 点击一个视频
	 */
	public static void clickVideo()
	{
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		UiDevice.getInstance().click(x , y);
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
   
}
