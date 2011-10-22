package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.User")]
	public class UserF
	{
		public var   uid :int;
		public var   username:String;
		public var   password:String;
		public var   realname:String;
		public var   address:String;
		public var   phone:String;
		public var   postcode:String;
		public var   money:Number//会员金额
		
		
		public function UserF()
		{
		}
	}
}