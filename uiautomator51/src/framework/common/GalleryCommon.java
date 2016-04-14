package framework.common;


import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
//import static framework.data.ResIdTextAndDesc.Devices_Desc_PictureStore;
import static framework.data.ResIdTextAndDesc.Camera_Text_Operator_camera;
import static framework.data.ResIdTextAndDesc.Devices_Desc_PictureStore;
import static framework.excute.Excute.*;

import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
/**
 * 
 * @author SPREADTRUM\shuo.guan
 *
 */
public class GalleryCommon 
{
	/**
	 * 将所有图片删除
	 */
   public static void deleteMoreGallery()
    {
    	UiDevice.getInstance().pressMenu();
    	excute(Object_Text, Operation_ClickWait,"选择相册");
    	if ((Boolean) excute(Object_Text, Operation_Exists,"选中了 0 项"))
    	{
    		excute(Object_Text, Operation_ClickWait,"选中了 0 项");
    		excute(Object_Text, Operation_ClickWait,"全选");
    		excute(Object_Description, Operation_ClickWait,"更多选项");
   
    		excute(Object_Text, Operation_ClickWait,"删除");
    		excute(Object_Text, Operation_ClickWait,"确定");
    	}
    	    excute(Object_Text, Operation_ClickWait,"相机");
    	    excute(Object_ResourceId, Operation_ClickWait,"com.android.camera2:id/shutter_button");
    	    UiDevice.getInstance().pressBack();
    }
   /**
    * 获取更多图片（2张），因为有些是要以群组展开的case
    */
   public static void morePicture() 
   {
	   excute(Object_ResourceId, Operation_ClickWait,"com.android.gallery3d:id/action_camera");
	   excute(Object_ResourceId, Operation_ClickWait,"com.android.camera2:id/shutter_button");
	   check(Object_ResourceId ,Operation_WaitForExists,"com.android.camera2:id/btn_beauty_button","30000");
	   excute(Object_ResourceId, Operation_ClickWait,"com.android.camera2:id/shutter_button");
	   check(Object_ResourceId ,Operation_WaitForExists,"com.android.camera2:id/btn_beauty_button","30000");
	   //excute(Object_ResourceId, Operation_ClickWait,"com.android.camera2:id/shutter_button");
	   UiDevice.getInstance().pressBack();
	}
   /**
    * 单击图片（也就是正中间的位置的图片）
    */
   public static void clickGallery()
   {
	    Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.gallery3d:id/gl_root_view");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x , y);
		//check(Object_ResourceId ,Operation_WaitForExists,"android:id/action_bar_spinner","30000");
   }
   /**
    * 点击一个群组
    */
   public static void clickGroup()
   {
	   excute(Object_Description, Operation_ClickWait,"更多选项");
	   excute(Object_Text, Operation_ClickWait,"选择相册");
	   GalleryCommon.clickGallery();
    } 
   }	
    	
    	
    	
    	
    	
  
    	