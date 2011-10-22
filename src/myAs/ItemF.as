package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.Item")]
	public class ItemF
	{
	
		public var item_id : int;
		public var item_name : String;
		public var standard : String;	//规格
		public var product_num : String;	//产品编号
		public var img_url : String;	//产品编号
		public var store_num :Number;//库存
		public var categoryName :String;//分类名
		public var  e_item_name : String; // 英文名称
		public var  intro:String; // 功效主治
		
		public var  providerName :String ;
		public var  cost :Number;	//进价
		public var  price:Number;	//售价		
		
		public var ipid :int;
		public function ItemF()
		{
		}

	}
}

