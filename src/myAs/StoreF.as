package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.Store")]
	public class StoreF
	{
		
		public var  storeid :int;   //仓库ID
		public var  name : String;   //仓库名称
		public var  location : String;   //地址
		public var  memo :  String;     //备注
		public var  upflag :String;    //发送标志
		
		
		
		
		
		
		
		
		public function StoreF()
		{
		}
	}
}