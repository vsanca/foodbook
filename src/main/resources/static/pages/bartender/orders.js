angular.module('foodbook').controller('bartenderOrdersController', function($scope, $http, $state,$location, sessionService , notifyService,authenticationService) {
	
$scope.activePageNumber = 2;
	
	
	
	$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.chef = data;
				$scope.chef.id = sessionService.getUserInfo().userId;
				
				/*$http.get('/user/chef/allUnfinishedOdersTEST/'+sessionService.getUserInfo().userId, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						
									.success(function (data) {
							
										console.log(data);
										$scope.myOrdersDTO = data;
							
							
							
						});*/
				
				
	});
	
	
	/*
	$http.get('/user/chef/allUnfinishedOdersTEST/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			
						.success(function (data) {
				
							console.log(data);
							$scope.myOrdersDTO = data;
				
				
				
			});
	
	
	
	
	$scope.acceptOrder = function(o){
		
		$http.get('/user/chef/acceptedOrder/'+ o.myId +'/'+ sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.myOrdersDTO = data;
					
					
					notifyService.showSuccess('Porudžbina prihvaćena!');
				})
				.error(function() {
					notifyService.showError('Porudžbina nije uspešno prihvaćena!');
				});
	}
	
	$scope.createOrder = function(o){
		$http.get('/user/chef/createdOrder/'+ o.myId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					
					$scope.myOrdersDTO = data;
					
					
					notifyService.showSuccess('Uspešno sačuvano: obrok napravljen!');
				})
				.error(function() {
					notifyService.showError('Nije uspešno sačuvano: obrok nije napravljen!!!');
				});
	}
	*/
});