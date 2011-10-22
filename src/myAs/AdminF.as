package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.Admin")]
	public class AdminF
	{
		public  var aid : int;
		public var password : String;
		public  var username: String;
		public  var gender : int;
		public  var rank : int;		//管理员权限
		public  var addDate : Date;
		
		public function AdminF()
		{
		}
	}
}


