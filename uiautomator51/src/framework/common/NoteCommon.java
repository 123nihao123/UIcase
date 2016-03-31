package framework.common;

import static framework.data.ObjectType.Object_Description;

import static framework.data.OperationType.*;
import static framework.data.ObjectType.Object_Device;
import static framework.data.ObjectType.Object_ResourceId;
import static framework.data.ObjectType.Object_Text;
import static framework.data.OperationType.Operation_ClickWait;
import static framework.data.OperationType.Operation_Exists;
import static framework.data.OperationType.Operation_PressBack;
import static framework.data.OperationType.Operation_PressMenu;
import static framework.data.OperationType.Operation_SetText;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.excute;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class NoteCommon {
	
//	public static void createFolder(String name) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: createFolder======");
//		
//		new FindAppByDescAndClick("更多选项").action();
//		new FindAppByTextAndClick("新建文件夹").action();	
//		new FindAppByResIdAndSetText(Note_ResId_New_Folder, name).action();
//		new FindAppByTextAndClick("确定").action();
//		new Wait(2000).action();
//	}
//
//	public static void deleteAll() throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: deleteAll======");
//		new FindAppByDescAndClick("更多选项").action();
//		new FindAppByTextAndClick("删除全部").action();
//		new FindAppByTextAndClick("确定").action();
//		new Wait(2000).action();
//	}
//	
//	public static void deleteNote(String name) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: deleteNote======");
//		new FindAppByTextAndLongClick(name).action();
//		new FindAppByTextAndClick("删除").action();
//		new FindAppByTextAndClick("确定").action();
//		new Wait(2000).action();
//	}
//
//	public static void deleteMoreWord(int wordNum) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: deleteMoreWord======");
//		
//		for(int i = 0; i < wordNum; i++)
//		{
//			new PressDelete().action();
//		}
//	}
//	
//	public static void saveNote(String noteName) throws UiObjectNotFoundException
//	{
//		System.out.println("======Start to excute NoteCommon: saveNote======");
//		new FindAppByDescAndClick("更多选项").action();
//		if(!noteName.equals("None"))
//		{
//			new FindAppByTextAndClick("编辑标题").action();
//			new FindAppByResIdAndSetText(Note_ResId_Edit_Title, noteName).action();
//			new FindAppByTextAndClick("确定").action();
//			new FindAppByDescAndClick("更多选项").action();
//		}
//		new FindAppByTextAndClick("保存").action();
//		new Wait(2000).action();
//	}
	
	/**
	 * 新建一个笔记
	 */
	public static void createNote(String NoteTitle,String NoteContent)
	{
		excute(Object_Description,Operation_ClickWait,"新建便签");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.note:id/et_content",NoteContent);
		excute(Object_Device,Operation_PressBack);
		excute(Object_Text,Operation_ClickWait,"编辑标题");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.note:id/et_dialog_new_folder",NoteTitle);
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Device,Operation_PressBack);	
	}
	
	/**
	 * 新建一个文件夹
	 */
	public static void createFolder(String FolderName)
	{
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"新建文件夹");
		excute(Object_ResourceId,Operation_SetText,"com.sprd.note:id/et_dialog_new_folder",FolderName);
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 如果存在笔记或者文件夹，删除全部内容
	 */
	public static void deleteAll()
	{
		if(!(Boolean)excute(Object_Text, Operation_Exists, "设置新的便签，请点击创建便签"))
		{
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"删除全部");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
	}
}
