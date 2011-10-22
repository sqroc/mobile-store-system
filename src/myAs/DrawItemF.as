package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.DrawItem")]
	public class DrawItemF
	{
		
		
		public var  drawItemId : int; // 单据ID
		public var  code :  String; // 单据编号
		public var  billDate : Date ; // 单据日期
		public var  operator : String ; // 操作员姓名
		public var  drawMan : String; // 领料人姓名
		public var  store : Object; // 仓库ID Store
		public var  memo : String; // 备注
		public var  updatetime : Date; // 修改日期
		public var  rptid : int; // 打印样式ID
		
		public function DrawItemF()
		{
		}
	}
}