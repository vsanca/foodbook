angular.module('foodbook').controller('bartenderProfileController', function($scope, $http, $state, $location) {
	
	$scope.activePageNumber = 0;
	
	$scope.home = function() {
        $state.transitionTo('bartender.home');
    };

    

    /*
    $scope.logout = function () {
        $state.transitionTo('logout');
    };
    */
});