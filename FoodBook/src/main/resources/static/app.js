/**
 * Created by Viktor on 12/18/2016.
 */

(function () {
    'use strict';

    angular.module('foodbook', ['ngRoute']).config(['$routeProvider', function ($routeProvider) {
        $routeProvider

            .when('/login', {
                templateUrl: 'user/login.html',
                controller: 'LoginController'
            })

            .when('/guest/profile-page', {
                templateUrl: 'guest/profilePage/profilePage.html',
                controller: 'ProfilePageController'
            });

    }]).run(run);

    run.$inject = ["$rootScope", "$location", 'sessionService'];

    function run($rootScope, $location, sessionService) {
        console.log("Application ready to go!");

        if (sessionService.getUserInfo() === null) {
            $location.path("/login");
        }

        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (sessionService.getUserInfo() === null) {
                // no logged user, we should be going to #login
                if (next.templateUrl !== "user/login.html") {
                    console.log("Not logged in! Redirecting to login...");
                    // not going to #login, we should redirect now
                    $location.path("/login");
                }
            }
        });
    }

})();