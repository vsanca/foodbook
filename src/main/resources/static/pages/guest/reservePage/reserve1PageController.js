/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('Reserve1PageController', Reserve1PageController);

  Reserve1PageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', '$stateParams', 'authenticationService'];

  function Reserve1PageController($scope, $state, $location, sessionService, guestService, stateParams, authenticationService) {
    alert("restaurants blabla");
    $scope.userInfo = sessionService.getUserInfo(); 

    guestService.getReserve1PageInfo($scope.userInfo.userId).then(function (response) {
        $scope.reserve1Page = response.data; 
    }, function (error) {

    });

    $scope.logoff = function() {
      alert("logoff called");
      authenticationService.logoff(); 
      $state.go('login');    
    }   

    console.log($stateParams); 
    console.log($stateParams.restaurantId); 
  }
})();

 
    