'use strict';

angular.module('commentDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('commentDetails', {
                parent: 'app',
                url: '/companies/details/:comId',
                template: '<com-details></com-details>'
            })
    }]);