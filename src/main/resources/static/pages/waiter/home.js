angular.module('foodbook').controller('waiterHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.activePageNumber = 0;
	
	$scope.waiter = {};
	
	$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.waiter = data;
				
				$scope.waiter.id = sessionService.getUserInfo().userId;
				
				if(data.password_set === false){
					$("#myModal").modal();
				}
	});
	
	$scope.modifyWaiter = function() {
		$http.post('/user/waiter/update', $scope.waiter,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Podaci uspešno modifikovani!');
				})
				.error(function() {
					notifyService.showError('Greška prilikom modifikacije!');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/waiter/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.waiter = data;
		});
	};
	
	$scope.changePassword = function() {
		$http.post('user/waiter/changePassword/', $scope.waiter,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izmenjena lozinka.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene lozinke.');
				});
	};
	
	
	
});