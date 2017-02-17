/**
 * Holds ENUM values from backend and other constant values
 * @author Jelena Jankovic RA139-2013
 *
 */
(function() {
    'use strict';

    angular
        .module('foodbook')
        .constant('userRoles', {
            'GUEST' : 0,
            'CHEF' : 1,
            'WAITER': 2,
            'BARTENDER' : 3,
            'MANAGER': 4,
            'SYS_MANAGER': 5,
            'BIDDER': 6
        });
})();