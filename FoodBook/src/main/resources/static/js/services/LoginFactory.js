/**
 * Created by Viktor on 12/21/2016.
 */

angular.module('foodbookApp.LoginFactory', [])
    .factory('LoginFactory', function ($http) {
        var factory = {};

        factory.getUser = function (user) {
            return $http.post('/login',{"email":user.email, "password":user.password});
        };

        return factory;
    });