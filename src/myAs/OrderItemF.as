package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.OrderItem")]
	public class OrderItemF
	{
		
		
		public var oid :int;
		public var item :ItemF;
		public var  number : int ;      //数量
		public var price : double;    //单个商品价格

		
		
		public var  itemId :int;//物品好
		public var  cartId :int ;//订单号
		
		
		
		
		public function OrderItemF()
		{
		}
	}
}

