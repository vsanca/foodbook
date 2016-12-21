/**
 * Created by Viktor on 12/18/2016.
 */

var app = angular.module('foodbookApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : '/html/login.html'
        })
        .when('/registerGuest',{
            templateUrl : 'html/registerGuest.html'
        })
        .when('/guestProfile',{
            templateUrl : 'html/guestProfile.html'
        })
        .when('/login',{
            templateUrl : 'html/login.html'
        })
        .when('/confirmRegistration',{
            templateUrl : 'html/confirmRegistration.html'
        })
}]);