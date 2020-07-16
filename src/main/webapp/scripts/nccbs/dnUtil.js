
function getTaxRate(){
	
	var taxRate = "";
	var param = "";
	$.ajax({
		url : '/distributor/logistics/orderDeliveryMgt/orderPlacement/getTaxRate.json',
		type : 'POST',
		async: false,
		data : param,
		success : function(data) {
			console.log(data);
			taxRate = data.result;
		},
	    error: function(e){
	        alert(e.reponseText);
	    },
		complete : function() {
		}
	});
	
	return taxRate;
}