package framework.driver;


import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;

public class OperationScroll 
{
	//通过text滑动找到控件，若找到返回true，否则返回false
	public static boolean scrollIntoText(UiScrollable uiScrollable, String text) throws UiObjectNotFoundException  
	{
		return uiScrollable.scrollTextIntoView(text);
	}	
	
	//通过定义的object滑动找到控件，若找到返回true，否则返回false
	public static boolean scrollIntoView(UiScrollable uiScrollable,UiObject uiObject) throws UiObjectNotFoundException  
	{
		return uiScrollable.scrollIntoView(uiObject);
	}	
}