angular.module('foodbook').controller('sysmanagerHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.sysmanager = {};
	
	$http.get('sysmanager/get/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.sysmanager = data;
	});
	
	$scope.modifySysManager = function() {
		$http.post('sysmanager/update', $scope.sysmanager,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izvršena izmena.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene.');
				});
	};
	
	$scope.cancel = function() {
		$http.get('sysmanager/get/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.sysmanager = data;
		});
	};
});