'use strict';

angular.module('comDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('comDetails', {
                parent: 'app',
                url: '/companies/details/:comId',
                template: '<com-details></com-details>'
            })
    }]);