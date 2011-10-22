package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.Provider")]
	public class ProviderF
	{
		
		
		
		
		public var pid : int;
		public var name :String;
		public var postcode : String;
		public var address : String;
		public var phone : String;
		public var supervisor :String;   //负责人

		public function ProviderF()
		{
		}
	}
}