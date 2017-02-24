angular.module('foodbook').controller('bartenderHomeController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.bartender = {};
	
	$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.bartender = data;
				$scope.bartender.id = sessionService.getUserInfo().userId;
				
				if(data.password_set === false){
					$("#myModal").modal();
				}
				
	});
	
	$scope.modifyBartender = function() {
		$http.post('/user/bartender/update', $scope.bartender,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Podaci uspešno modifikovani!');
				})
				.error(function() {
					notifyService.showError('Greška prilikom modifikacije!');
				});
	};
	
	$scope.cancel = function() {
		$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId, 
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					$scope.bartender = data;
		});
	};
	
	
	
	$scope.changePassword = function() {
		$http.post('user/bartender/changePassword/', $scope.bartender,
				{ headers: { 'Authorization': sessionService.getAuthToken() } })
				.success(function (data) {
					notifyService.showSuccess('Uspešno izmenjena lozinka.');
				})
				.error(function() {
					notifyService.showError('Greška prilikom izmene lozinke.');
				});
	};
	
});