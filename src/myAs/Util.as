package myAs
{
	import com.as3xls.xls.Cell;
	import mx.collections.ArrayCollection;
	import flash.events.*;
	import flash.net.FileReference;
	import com.as3xls.xls.Sheet;
	import com.as3xls.xls.ExcelFile;
	import mx.controls.Alert;
	import mx.controls.DataGrid;
	import mx.controls.dataGridClasses.DataGridColumn;
	import flash.utils.ByteArray;
	import mx.controls.Alert;

	

	public class Util
	{
		
		public function Util()
		{
		}
		
		
		
		/**导出Excel表格函数，参数为DataGrid**/
		public static function exportToExcel(myDg:DataGrid):String
		{  
			
			var fields:Array = new Array(); 
			/**生成表对象sheet**/
			var sheet:Sheet= new Sheet();
			
			var dataProviderCollection:ArrayCollection =myDg.dataProvider as ArrayCollection;
			/**获得表格的行数**/
			var rowCount:int =  dataProviderCollection.length;
			/**设置表格的行数(rowCount+1)，列数（myDg.columnCount）**/
			sheet.resize(rowCount+1,myDg.columnCount);
			/**获得DateGrid列的内容**/
			var columns:Array = myDg.columns; 
			/**循环设置列名的值**/
			var i:int = 0; 
			for each (var field:DataGridColumn in columns)
			{  
				fields.push(field.dataField.toString()); 
				/**第一行的值,取值为myDg的headerText**/
				sheet.setCell(0,i,field.headerText.toString()); 
				i++; 
			}
			/**循环设置行的值**/
			for(var r:int=0;r<rowCount;r++)
			{
				/**获得dataProviderCollection的每行Item的对象**/
				var record:Object =dataProviderCollection.getItemAt(r);
				/**调用回调函数写入sheet**/
				insertRecordInSheet(r+1,sheet,record);
			}
			/**生成Excel文件**/
			var xls:ExcelFile = new ExcelFile();
			/**将sheet写入Excel文件中**/
			xls.sheets.addItem(sheet);
			/**将xls对象转换为ByteArray流对象**/
			var bytes: ByteArray = xls.saveToByteArray();
			/**生成新文件域**/
			var fr:FileReference = new FileReference();
			/**将bytes流对象保存到文件域**/
			
	
			fr.save(bytes,"SampleExport.xls");
	
			
            return "succeed";
			
			
			/**回调函数**/
			function insertRecordInSheet(row:int,sheet:Sheet,record:Object):void
			{
				var colCount:int = myDg.columnCount;
				for(var c:int; c < colCount; c++) 
				{ 
					var i:int = 0; 
					for each(var field:String in fields)
					{ 
						for each (var value:String in record)
						{ 
							/**循环判断myDg列名域值record[field]与value是否相等**/
							if (record[field].toString() == value) 
								/**写入表格中**/
								sheet.setCell(row,i,value); 
						} 
						i++; 
					} 
				}
			}
		}
	}
}