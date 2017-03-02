/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('RegisterGuestController', RegisterGuestController);

  RegisterGuestController.$inject = ['$scope', '$location', 'guestService', 'notifyService'];

  function RegisterGuestController($scope, $location, guestService, notifyService) {

    $scope.newGuest = {
      name: null,
      surname: null,
      email: null,
      password: null,
      address: null
  /*    password1: null */
    };
    $scope.confirmPassword = "";
    $scope.passwordCssClass = "";
    $scope.checkPasswords = function() {
      if($scope.confirmPassword !== $scope.newGuest.password) {
       $scope.passwordCssClass = "invalid";
       notifyService.showError("Passwords have to be equal!");
      } else {
        $scope.passwordCssClass = "";
      }
    };
    $scope.registerGuest = function () {

      if($scope.newGuest.email === null || $scope.newGuest.email === "") {
        notifyService.showError("Email is required!");
        return;
      }

    if($scope.newGuest.name === null || $scope.newGuest.name === "") {
        notifyService.showError("Name is required!");
        return;
      }

  if($scope.newGuest.surname === null || $scope.newGuest.surname === "") {
        notifyService.showError("Surname is required!");
        return;
      }


      if($scope.newGuest.address === null || $scope.newGuest.address === "") {
        notifyService.showError("Address is required!");
        return;
      }

      if($scope.newGuest.password === null || $scope.newGuest.password === "") {
        notifyService.showError("Password is required!");
        return;
      }

       if($scope.confirmPassword !== $scope.newGuest.password) {
       notifyService.showError("Passwords have to be equal!");
       return;
      }

      guestService.register($scope.newGuest).then(function (response) {
        notifyService.showSuccess("Registration successfull, check your email in order to verify your account.");
        $location.path("/login");
      }, function (error) {

      });
    };
  }
})();