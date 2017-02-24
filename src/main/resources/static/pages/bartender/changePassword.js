angular.module('foodbook').controller('bartenderChangePasswordController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.oldPw = '';
	$scope.newPw = '';
	$scope.confirmedPw = '';
	
	$scope.user = {};
	
	$http.get('/user/bartender/profile/'+sessionService.getUserInfo().userId, 
			{ headers: { 'Authorization': sessionService.getAuthToken() } })
			.success(function (data) {
				$scope.user = data;
	});
	
	$scope.changeMyPassword = function() {
		if(($scope.oldPw !== $scope.newPw) && ($scope.newPw === $scope.confirmedPw)){
			$scope.user.testOldPw = $scope.oldPw;
			$scope.user.testNewPw = $scope.newPw;
			$http.post('/user/bartender/updatePw', $scope.user,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						$scope.user = data;
						notifyService.showSuccess('Lozinka uspešeno modifikovana!');
					})
					.error(function() {
						notifyService.showError('Greška prilikom izmene lozinke!');
					});
		}
		else{
			notifyService.showError('Greška prilikom izmene lozinke!5555555555');
		}
	};
	
	$scope.returnBack = function() {
		$state.go('bartender.home');
	};
	
});