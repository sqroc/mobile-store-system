package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.ItemProvider")]
	public class ItemProviderF
	{
		public var  ipid : int;
		public var  provider : ProviderF ;
		public var  item :ItemF;
		public var  cost : Number;	//进价
		public var  price : Number;	//售价		
		public var pid : int;
		public var item_name :String;
		public var pName :String; 
		public var itid :int;
		
		
	public function ItemProviderF()
		{
		}
	}
}