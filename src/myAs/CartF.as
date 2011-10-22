package myAs
{[Bindable]
	[RemoteClass(alias = "ms.model.Cart")]
	
	public class CartF
	{
		
		public var  cart_id : int;      //购物车总订单号
		public var  date : Date;         //下订单时间
		public var  state : String;       //订单状态
		public var  allprice : Number;    //总价格
		
		public var  user :Object;
		
		
		public var  userId : int;
		public var  num :String;
        
		public function CartF()
		{
		}
	}
}