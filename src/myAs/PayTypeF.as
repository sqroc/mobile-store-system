package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.PayType")]
	public class PayTypeF
	{
		
	
		public  var  ptid : int; // ID
		public  var  name :String ; // 付款名称
		public  var  bankId : String; // 帐号ID
		public  var  detail :String ; // 付款说明
		public  var  memo : String; // 备注
		public function PayTypeF()
		{
		}
	}
}