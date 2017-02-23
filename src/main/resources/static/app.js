/**
 * IFFE is used to avoid dirtying global namespace and or potential conflicts.
 * EACH module, controller, service should be contained within its own scope by wrapping it within an IIFE.
 * .$inject is used to specify required DependencyInjection; for code to remain functional after minification/obfuscation.
 */
(function () {
    'use strict';

    angular.module('foodbook', ['ngRoute', 'cgNotify', 'ui.router', 'ui.bootstrap']).config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    	
    	$urlRouterProvider.otherwise('/login');
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
	    	.state('manager.groceries', {
	    		url: '/groceries',
	    		templateUrl: 'pages/manager/groceries.html',
	    		controller: 'managerGroceriesController'
	    	})
	    	.state('manager.menu', {
	    		url: '/menu',
	    		templateUrl: 'pages/manager/menu.html',
	    		controller: 'managerMenuController'
	    	})
	    	.state('manager.registerWorker', {
	    		url: '/registerWorker',
	    		templateUrl: 'pages/manager/registerWorker.html',
	    		controller: 'managerRegisterWorkerController'
	    	})
	    	.state('manager.report', {
	    		url: '/report',
	    		templateUrl: 'pages/manager/report.html',
	    		controller: 'managerReportController'
	    	})
	    	.state('manager.restaurant', {
	    		url: '/restaurant',
	    		templateUrl: 'pages/manager/restaurant.html',
	    		controller: 'managerRestaurantController'
	    	})
	    	.state('manager.setLayout', {
	    		url: '/layout',
	    		templateUrl: 'pages/manager/setLayout.html',
	    		controller: 'managerSetLayoutController'
	    	})
	    	.state('manager.setTimetable', {
	    		url: '/timetable',
	    		templateUrl: 'pages/manager/setTimetable.html',
	    		controller: 'managerSetTimetableController'
	    	})
	    	.state('manager.workers', {
	    		url: '/workers',
	    		templateUrl: 'pages/manager/workers.html',
	    		controller: 'managerWorkersController'
	    	})
	    	.state('bidder', {
	    		url: '/bd',
	    		templateUrl: 'pages/bidder/profile.html',
	    		controller: 'bidderProfileController'
	    	})
	    	.state('bidder.home', {
	    		url: '/panel',
	    		templateUrl: 'pages/bidder/home.html',
	    		controller: 'bidderHomeController'
	    	})
	    	.state('bidder.overview', {
	    		url: '/overview',
	    		templateUrl: 'pages/bidder/overviewBidding.html',
	    		controller: 'bidderOverviewBiddingController'
	    	})
	    	.state('bidder.biddings', {
	    		url: '/biddings',
	    		templateUrl: 'pages/bidder/biddings.html',
	    		controller: 'bidderBiddingsController'
	    	})
	    	.state('bidder.newBidding', {
	    		url: '/newBidding',
	    		templateUrl: 'pages/bidder/newBidding.html',
	    		controller: 'bidderNewBiddingController'
	    	})
		    .state('bartender', {
				url: '/bartender',
				templateUrl: 'pages/bartender/profile.html',
				controller: 'bartenderProfileController'
			})
			.state('bartender.home', {
				url: '/panel',
				templateUrl: 'pages/bartender/home.html',
				controller: 'bartenderHomeController'
			})
			.state('bartender.workSchedule', {
				url: '/workSchedule',
				templateUrl: 'pages/bartender/workSchedule.html',
				controller: 'bartenderWorkScheduleController'
			})
			.state('bartender.orders', {
				url: '/orders',
				templateUrl: 'pages/bartender/orders.html',
				controller: 'bartenderOrdersController'
			})
			.state('chef', {
				url: '/chef',
				templateUrl: 'pages/chef/profile.html',
				controller: 'chefProfileController'
			})
			.state('chef.home', {
				url: '/panel',
				templateUrl: 'pages/chef/home.html',
				controller: 'chefHomeController'
			})
			.state('chef.workSchedule', {
				url: '/workSchedule',
				templateUrl: 'pages/chef/workSchedule.html',
				controller: 'chefWorkScheduleController'
			})
			.state('chef.orders', {
				url: '/orders',
				templateUrl: 'pages/chef/orders.html',
				controller: 'chefOrdersController'
			})
				    .state('waiter', {
				url: '/waiter',
				templateUrl: 'pages/waiter/profile.html',
				controller: 'waiterProfileController'
			})
			.state('waiter.home', {
				url: '/panel',
				templateUrl: 'pages/waiter/home.html',
				controller: 'waiterHomeController'
			})
			.state('waiter.workSchedule', {
				url: '/workSchedule',
				templateUrl: 'pages/waiter/workSchedule.html',
				controller: 'waiterWorkScheduleController'
			})
			.state('waiter.restaurantRegion', {
				url: '/restaurantRegion',
				templateUrl: 'pages/waiter/restaurantRegion.html',
				controller: 'waiterRestaurantRegionController'
			})
			.state('waiter.orders', {
				url: '/orders',
				templateUrl: 'pages/waiter/orders.html',
				controller: 'waiterOrdersController'
			});
    	
    }).run(run);

    // required DependencyInjection
    run.$inject = ["$rootScope", "$location", '$state', 'sessionService'];

    function run($rootScope, $location, sessionService) {
        console.log("Application ready to go!");

        if (sessionService.getUserInfo === null && $location.path() !== 'pages/guest/confirm-registration') {
            $state.go('login');
        }

        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (sessionService().getUserInfo() === null) {
                // no logged user, we should be going to #login
                if (next.templateUrl !== "pages/user/login.html" && next.templateUrl !== "pages/guest/register/registerGuest.html" && next.templateUrl !== "pages/guest/confirmRegistration/confirmRegistration.html") {
                    console.log("Not logged in! Redirecting to login...");
                    // not going to #login, we should redirect now
                    $state.go('login');
                }
            }
        });
    };

})();