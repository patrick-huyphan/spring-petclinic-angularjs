'use strict';

angular.module('comList', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('companies', {
                parent: 'app',
                url: '/companies',
                template: '<com-list></com-list>'
            })
    }]);