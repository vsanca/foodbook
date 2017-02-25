/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('ConfirmRegistrationController', ConfirmRegistrationController);

  ConfirmRegistrationController.$inject = ['$scope','$location', '$routeParams' , 'guestService'];

  function ConfirmRegistrationController($scope, $location, $routeParams, guestService) {
    console.log("ConfirmRegistrationController ready!");
    $scope.infoMessage = "";
    let token = $routeParams.token;
    
    guestService.verifyGuest(token).then(function() {
     $scope.infoMessage = "Account verified, now you can login.";
    }, function() {
        $scope.infoMessage = "Account verification failed, invalid verification link!";
    });
    
    
  }
  
  
})();
