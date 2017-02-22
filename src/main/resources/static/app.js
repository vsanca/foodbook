/**
 * Created by Viktor on 12/18/2016.
 */

var foodbook = angular.module('foodbook', ['ngRoute', 'cgNotify', 'ui.router', 'ui.bootstrap']);

foodbook.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
	
	//$urlRouterProvider.otherwise('/login')
    $stateProvider
    	.state('login', {
    		url: '/login',
    		controller: 'LoginController',
    		templateUrl: 'pages/user/login.html',
    	})
        .state('register', {
        	url: '/guest/register',
        	templateUrl: 'pages/guest/register/registerGuest.html',
            controller: 'RegisterGuestController'
        })
        .state('guest-profile', {
        	url: '/guest/profile-page',
        	templateUrl: 'pages/guest/profilePage/profilePage.html',
            controller: 'ProfilePageController'
        })
        .state('guest-confirm', {
        	url: '/guest/confirm-registration',
        	templateUrl: 'pages/guest/confirmRegistration/confirmRegistration.html',
            controller: 'ConfirmRegistrationController'  
        })
        .state('guest-home', {
            url: '/guest/home-page',
        	templateUrl: 'pages/guest/homePage/homePage.html',
            controller: 'HomePageController'
        })
        .state('guest-friends', {
            url: '/guest/friends-page',
        	templateUrl: 'pages/guest/friendsPage/friendsPage.html',
            controller: 'FriendsPageController'
        })
        .state('guest-restaurants', {
            url: '/guest/restaurants-page',
        	templateUrl: 'pages/guest/restaurantsPage/restaurantsPage.html',
            controller: 'RestaurantsPageController'
        })
    	.state('sysmanager', {
    		url: '/sm',
    		templateUrl: 'pages/sysmanager/profile.html',
    		controller: 'sysmanagerProfileController'
    	})
    	.state('sysmanager.home', {
    		url: '/panel',
    		templateUrl: 'pages/sysmanager/home.html',
    		controller: 'sysmanagerHomeController'
    	})
    	.state('sysmanager.addSystemManager', {
    		url: '/addSystemManager',
    		templateUrl: 'pages/sysmanager/addSystemManager.html',
    		controller: 'sysmanagerAddSystemManagerController'
    	})
    	.state('sysmanager.addRestaurantManager', {
    		url: '/addRestaurantManager',
    		templateUrl: 'pages/sysmanager/addRestaurantManager.html',
    		controller: 'sysmanagerAddRestaurantManagerController'
    	})
    	.state('sysmanager.addRestaurant', {
    		url: '/addRestaurant',
    		templateUrl: 'pages/sysmanager/addRestaurant.html',
    		controller: 'sysmanagerAddRestaurantController'
    	})
    	.state('sysmanager.addBidder', {
    		url: '/addBidder',
    		templateUrl: 'pages/sysmanager/addBidder.html',
    		controller: 'sysmanagerAddBidderController'
    	})
    	.state('manager', {
    		url: '/rm',
    		templateUrl: 'pages/manager/profile.html',
    		controller: 'managerProfileController'
    	})
    	.state('manager.home', {
    		url: '/panel',
    		templateUrl: 'pages/manager/home.html',
    		controller: 'managerHomeController'
    	})
    	.state('bidder', {
    		url: '/bd',
    		templateUrl: 'pages/bidder/profile.html',
    		controller: 'bidderProfileController'
    	})
    	.state('bidder.home', {
    		url: 'panel',
    		templateUrl: 'pages/bidder/home.html',
    		controller: 'bidderHomeController'
    	})
	    .state('bartender', {
			url: '/br',
			templateUrl: 'pages/bartender/profile.html',
			controller: 'bartenderProfileController'
		})
		.state('bartender.home', {
			url: 'panel',
			templateUrl: 'pages/bartender/home.html',
			controller: 'bartenderHomeController'
		});
    	

}).run(function($rootScope, $state, $location, sessionService) {
    console.log("Application ready to go!");
    
    if (sessionService.getUserInfo() === null && $location.path() !== 'pages/guest/confirm-registration') {
    	$state.go('login');
    }

    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        if (sessionService.getUserInfo() === null) {
            // no logged user, we should be going to #login
            if (next.templateUrl !== "pages/user/login.html" && next.templateUrl !== "pages/guest/register/registerGuest.html" && next.templateUrl !== "pages/guest/confirmRegistration/confirmRegistration.html") {
                console.log("Not logged in! Redirecting to login...");
                // not going to #login, we should redirect now
                //$location.path("/login");
                $state.go('login');
            } 
        }
    });
});
	
/*

(function () {
    'use strict';

    angular.module('foodbook', )
    .config(function ($routeProvider, $stateProvider) {
    	
        $routeProvider.otherwise('/login')
        $stateProvider
        	.state('login', {
        		url: '/login',
        		controller: 'LoginController',
        		templateUrl: 'pages/user/login.html'
        	})
            .state('register', {
            	url: '/guest/register',
            	templateUrl: 'pages/guest/register/registerGuest.html',
                controller: 'RegisterGuestController'
            })
            .state('guest.profile', {
            	url: '/guest/profile-page',
            	templateUrl: 'pages/guest/profilePage/profilePage.html',
                controller: 'ProfilePageController'
            })
            .state('guest.confirm', {
            	url: '/guest/confirm-registration',
            	templateUrl: 'pages/guest/confirmRegistration/confirmRegistration.html',
                controller: 'ConfirmRegistrationController'  
            })
            .state('guest.home', {
                url: '/guest/home-page',
            	templateUrl: 'pages/guest/homePage/homePage.html',
                controller: 'HomePageController'
            })
            .state('guest.friends', {
                url: '/guest/friends-page',
            	templateUrl: 'pages/guest/friendsPage/friendsPage.html',
                controller: 'FriendsPageController'
            })
            .state('guest.restaurants', {
                url: '/guest/restaurants-page',
            	templateUrl: 'pages/guest/restaurantsPage/restaurantsPage.html',
                controller: 'RestaurantsPageController'
            });

    }).run(run);

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

//})();*/
