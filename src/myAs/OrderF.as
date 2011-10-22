package myAs
{
	[Bindable]
	[RemoteClass(alias = "ms.model.Order")]

    public class OrderF {
	   public  oid :int;
	   public  date : Date;
	   public  state : String ;       //订单状态
	   public  allprice : double;    //总价格

	   public function  OrderF()
	  {
	  }

}
}