package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.BackDraw")]
	public class BackDrawF
	{

		
		public var  backDrawId :int ; // 单据ID
		public var  code :String; // 单据号
		public var  billDate :Date; // 单据日期
		public var  operator : String; // 操作员
		public var  backMan : String; // 退货员
		public var  store :Object ; // 退入仓库号Store 
		public var  totalM : Number; // 总金额
		public var  memo : String; // 备注
		public var  updateTime : Date; // 修改日期
		public var  PTRID : int; // 打印样式
		public var  state : String; // 打印样式
		public var  user : Object;
		
		
		public function BackDrawF()
		{
		}
	}
}