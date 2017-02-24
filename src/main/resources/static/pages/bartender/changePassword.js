angular.module('foodbook').controller('bartenderChangePasswordController', function($scope, $http, $state, sessionService, notifyService) {
	
	$scope.oldPw = '';
	$scope.newPw = '';
	$scope.confirmedPw = '';
	
	$scope.changeMyPassword = function() {
		if(($scope.user.password === $scope.oldPw) && ($scope.newPw === $scope.confirmedPw) && ($scope.user.password !== $scope.newPw) ){
			$scope.user.password = $scope.newPw;
			$http.post('/user/bartender/update', $scope.user,
					{ headers: { 'Authorization': sessionService.getAuthToken() } })
					.success(function (data) {
						notifyService.showSuccess('Lozinka uspešeno modifikovana!');
					})
					.error(function() {
						notifyService.showError('Greška prilikom izmene lozinke!');
					});
		}
		else{
			notifyService.showError('Greška prilikom izmene lozinke!');
		}
	};
	
	$scope.returnBack = function() {
		$state.go('bartender.home');
	};
	
});