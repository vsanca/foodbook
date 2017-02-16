/**
 * Displays guest profile information, provides methods for editing guest account information
 * @author Jelena Jankovic RA139-2013
 *
 */
(function () {

  'use strict';

  angular.module('foodbookApp', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
      $routeProvider.when('/login', {
        templateUrl: 'user/login.html'
      });
    }]).controller('LoginController', LoginController)
    .service('authenticationService', authenticationService);

  LoginController.$inject = ['$scope', '$http', 'authenticationService', '$state'];
  
  function LoginController($scope, $http, authenticationService) {
      $scope.user = {
        username: null,
        password: null
      };
      
      $scope.login = function() {
        authenticationService.login($scope.user.username, $scope.user.password).then(function(data) {
          
          if(data.role === "GUEST") {
               $location.path( "/guest/home");
          }
        }, function(error) {

        });
      };
  }

})();