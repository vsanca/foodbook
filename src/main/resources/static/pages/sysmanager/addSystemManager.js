angular.module('foodbook').controller('sysmanagerAddSystemManagerController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.sysmanager_new = {};
	
	$scope.createSysManager = function () {
		
		$http.post('sysmanager/create', $scope.sysmanager_new,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno dodat menadžer sistema.');
					$scope.sysmanager_new = {};
				})
				.error(function() {
					notifyService.showError('Greška prilikom dodavanja menadžera sistema.');
				});
	};
	
	$scope.cancel = function() {
		$scope.sysmanager_new = {}
	};
	
});