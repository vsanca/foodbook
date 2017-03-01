/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('ConfirmRegistrationController', ConfirmRegistrationController);

  ConfirmRegistrationController.$inject = ['$scope','$location', '$stateParams' , 'guestService'];

  function ConfirmRegistrationController($scope, $location, $stateParams, guestService) {
    console.log("ConfirmRegistrationController ready!");
    $scope.infoMessage = "";
    console.log($stateParams);
    let token = $stateParams.guestId;
    
    guestService.verifyGuest(token).then(function() {
     $scope.infoMessage = "Account verified, now you can login.";
    }, function() {
        $scope.infoMessage = "Account verification failed, invalid verification link!";
    });
    
    
  }
  
  
})();
