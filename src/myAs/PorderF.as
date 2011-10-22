package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.Porder")]
	public class PorderF
	{
		
		public  var  porid : int;   // 采购订单主表ID
		public  var  code : String;    // 单据编号
		public  var  billDate :Date ; // 单据日期
		public  var  operator :String; // 操作员
		public  var  exeman :String; // 经办人
		public  var  provider : Object; // 供应商 Provider
		public  var  amount : Number; // 金额
		public  var  paytype :Object ; // 付款方式  //PayType
		public  var  revDate : Date; // 交货日期
		public  var  billto : String; // 交货地址
		public  var  memo : String; // 备注
		public  var  updateTime : Date; // 修改日期
		public  var  rptid : int; // 打印样式
		
		
		
		
		
		
		
		
		public function PorderF()
		{
		}
	}
}