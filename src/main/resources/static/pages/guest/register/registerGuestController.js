/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('RegisterGuestController', RegisterGuestController);

  RegisterGuestController.$inject = ['$scope', '$location', 'guestService'];

  function RegisterGuestController($scope, $location, guestService) {

    $scope.newGuest = {
      name: null,
      surname: null,
      email: null,
      password: null
  /*    password1: null */
    };

    $scope.registerGuest = function () {
      guestService.register($scope.newGuest).then(function (response) {
        $location.path("/login");
      }, function (error) {

      });
    };
  }
})();