package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.*;
import android.graphics.Rect;
import com.android.uiautomator.core.UiDevice;

public class HomeScreenCommon
{
	//屏幕宽度和高度
	public static int screenWidth = UiDevice.getInstance().getDisplayWidth();
	public static int screenHeight = UiDevice.getInstance().getDisplayHeight();
	
	//获取搜索栏边界坐标
	public static Rect getSearchplateBounds()
	{
		return (Rect)excute(Object_ResourceId,Operation_GetBounds,"com.android.quicksearchbox:id/search_plate");
	}
	//将指定控件删除
	public static void deleteView(int objectType, String text)
	{
		excute(objectType,Operation_DragToCoordinate,text,
				Integer.toString(getSearchplateBounds().centerX()),
				Integer.toString(getSearchplateBounds().centerY()), "20");
	}
}