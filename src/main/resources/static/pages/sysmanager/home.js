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
	
	$scope.changePassword = function () {
		$http.post('sysmanager/changePassword/', $scope.sysmanager,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izmenjena lozinka.');
					$scope.sysmanager.oldPassword = "";
					$scope.sysmanager.newPassword = "";
					$('#myModalPass').modal('hide');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene lozinke.');
					$scope.sysmanager.oldPassword = "";
					$scope.sysmanager.newPassword = "";
					$('#myModalPass').modal('hide');
				});
	};
	
});