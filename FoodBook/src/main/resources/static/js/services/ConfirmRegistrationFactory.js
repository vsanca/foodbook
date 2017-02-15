/**
 * Created by Viktor on 12/21/2016.
 */

angular.module('foodbookApp.ConfirmRegistrationFactory',[])
    .factory('ConfirmRegistrationFactory', function($http){
        var factory = {};

        factory.confirmGuest = function (user) {
            return $http.post('/confirmRegistration', {"email":user.email})
        };

        return factory;
    });