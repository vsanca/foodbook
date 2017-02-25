angular.module('foodbook').controller('managerMenuController', function($scope, $http, $state, sessionService, notifyService) {
	$scope.activePageNumber = 6;
	
	$scope.menuItems = [];
	$scope.menuItem = {};
	$scope.itemTypes = [];
	$scope.user = {};
	
	$http.get('/user/rmanager/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
				
				$http.get('/restaurant/menuItem/all/'+data.restaurant.id, 
						{ headers: { 'Authorization': sessionService.getAuthToken() } })
						.success(function (data) {
							$scope.menuItems = data;
				});
	});
	
	$http.get('/menu/itemTypes/all/', 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.itemTypes = data;
	});
	
	$scope.openModal = function() {
		
	};
	
	$scope.closeModal = function() {
		
	};
	
	$scope.addMenuItem = function() {
		
		$http.get('/restaurant/menu/' + $scope.user.restaurant.id, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.menuItem.menuId = data.id;
					
					$http.post('/restaurant/menuItem/create', $scope.menuItem,
							{ headers: { 'Authorization': sessionService.getAuthToken() } })
							.success(function (data) {
								
								notifyService.showSuccess('Uspešno dodata stavka menija.');
								$scope.closeModal();
								
							})
							.error(function() {
								notifyService.showError('Greška prilikom dodavanja stavke menija.');
							});
		});
	};
	
});