package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.BackDetail")]
	public class BackDetailF
	{
		
		
		
		public var  backDid : int; // 退货明细ID
		public var  backDraw : Object; // 主表IDBackDraw
		public var  item : Object; // 货品IDItem
		public var  quantity : int; // 数量
		public var  price : Number; // 该商品总价格
		public var  code : String ; // 该商品总价格
	
		
		public function BackDetailF()
		{
		}
	}
}