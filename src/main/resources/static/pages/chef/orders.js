angular.module('foodbook').controller('chefOrdersController', function($scope, $http, $state,$location, sessionService ,authenticationService) {
	
	$scope.activePageNumber = 2;
	
	$scope.myOrders= [];
	

	
	/*$http.get('/user/chef/allUnfinishedOders/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.orders= data;
				
	});*/
	
	$http.get('/user/chef/allUnfinishedOdersTEST/'+sessionService.getUserInfo().userId, 
	{ headers: { 'Authorization': sessionService.getAuthToken() } })
	.success(function (data) {
		$scope.myOrders= data;
		
		for(i=0; i<data.length; i++) {
			alert(data[i].item.item.name);
		}
		alert("USPELO");
		
	});
	
});