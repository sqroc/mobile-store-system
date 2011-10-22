package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.StoreInOut")]
	public class StoreInOutF
	{
		
		public var  inOutId : int; // 单据ID
		public var  code :String; // 单据编号
		public var  billDate :Date; // 单据日期
		public var  operator :String; // 操作员
		public var  store : Object; // 仓库ID     StoreF
		public var  satid :int; // 进/出标志
		public var  people :String; // 取料人
		public var  totalM :Number; // 总金额
		public var  memo :String; // 备注
		public var  updateTime :Date; // 修改日期
		public var  RPTID :int; // 打印样式
		
		
        public function StoreInOutF()
		{
		}
	}
}