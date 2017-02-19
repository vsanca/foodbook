/**
 * Created by Viktor on 12/18/2016.
 */

(function () {
    'use strict';

    angular.module('foodbook', ['ngRoute', 'cgNotify']).config(['$routeProvider', function ($routeProvider) {
        $routeProvider

            .when('/login', {
                templateUrl: 'pages/user/login.html',
                controller: 'LoginController'
            })
            .when('/guest/register', {
                templateUrl: 'pages/guest/register/registerGuest.html',
                controller: 'RegisterGuestController'
            })
            .when('/guest/profile-page', {
                templateUrl: 'pages/guest/profilePage/profilePage.html',
                controller: 'ProfilePageController'
            })
            .when('/guest/confirm-registration', {
              templateUrl: 'pages/guest/confirmRegistration/confirmRegistration.html',
              controller: 'ConfirmRegistrationController'  
            })
            .when('/guest/home-page', {
                templateUrl: 'pages/guest/homePage/homePage.html',
                controller: 'HomePageController'
            })
            .when('/guest/friends-page', {
                templateUrl: 'pages/guest/friendsPage/friendsPage.html',
                controller: 'FriendsPageController'
            })
            .when('/guest/restaurants-page', {
                templateUrl: 'pages/guest/restaurantsPage/restaurantsPage.html',
                controller: 'RestaurantsPageController'
            });

    }]).run(run);

    run.$inject = ["$rootScope", "$location", 'sessionService'];

    function run($rootScope, $location, sessionService) {
        console.log("Application ready to go!");

        if (sessionService.getUserInfo() === null && $location.path() !== 'pages/guest/confirm-registration') {
            $location.path("/login");
        }

        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (sessionService.getUserInfo() === null) {
                // no logged user, we should be going to #login
                if (next.templateUrl !== "pages/user/login.html" && next.templateUrl !== "pages/guest/register/registerGuest.html" && next.templateUrl !== "pages/guest/confirmRegistration/confirmRegistration.html") {
                    console.log("Not logged in! Redirecting to login...");
                    // not going to #login, we should redirect now
                    $location.path("/login");
                } 
            }
        });
    }

})();