/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbook').controller('LoginController', LoginController);

  LoginController.$inject = ['$scope', '$http', 'authenticationService', 'userRoles', '$location'];

  function LoginController($scope, $http, authenticationService, userRoles, $location) {
    console.log("LoginController ready!");
    $scope.user = {
      username: null,
      password: null
    };

    $scope.login = function () {
      authenticationService.login($scope.user.username, $scope.user.password).then(function (data) {

        if (data.role === userRoles["GUEST"]) {
          $location.path("/guest/profile-page");
        } else if (data.role === userRoles['CHEF']) {
          $location.path("/");
        } else if (data.role === userRoles['WAITER']) {
          $location.path("/");
        } else if (data.role === userRoles['MANAGER']) {
          $location.path("/");
        } else if (data.role === userRoles['BIDDER']) {
          $location.path("/");
        } else if (data.role === userRoles['BARTENDER']) {
          $location.path("/");
        } else if (data.role === userRoles['SYS_MANAGER']) {
          $location.path("/");
        } else {
          alert("LoginController::Invalid user role:::" + data.role);
          $location.path("/");
        }
      }, function (error) {
        alert("Login failed!");
      });
    };
  }

})();