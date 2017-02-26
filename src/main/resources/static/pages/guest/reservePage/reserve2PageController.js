/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('Reserve1PageController', Reserve1PageController);

  Reserve1PageController.$inject = ['$scope', '$state', '$location', 'sessionService', 'guestService', '$stateParams', 'authenticationService', 'loopify.ui.numberPicker'];

  function Reserve1PageController($scope, $state, $location, sessionService, guestService, $stateParams, authenticationService) {

    $scope.userInfo = sessionService.getUserInfo(); 

    guestService.getReserve1PageInfo($stateParams.restaurantId).then(function (response) {
        $scope.reserve2Page = response.data; 
    }, function (error) {

    });

    $scope.logoff = function() {
      alert("logoff called");
      authenticationService.logoff(); 
      $state.go('login');    
    }   
  /*
    console.log($stateParams); 
    console.log($stateParams.restaurantId); */
    $scope.reserve = function(id) {
      $state.go('reserve2/:restaurantId', {restaurantId: id}); 
    }
    
  }
})();

 
    