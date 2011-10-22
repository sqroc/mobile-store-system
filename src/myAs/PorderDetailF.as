package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.PorderDetail")]
	public class PorderDetailF
	{
		
		public var  pordid : int;
		public var  porder : Object; // 主表ID
		public var  item : Object; // 商品ID
		public var  quantity :int ; // 数量
		public var  price :Number ; // 总价格
		public var   store :Object ; // 进入仓库ID
		public var  code : String;
		public function PorderDetailF()
		{
		}
	}
}