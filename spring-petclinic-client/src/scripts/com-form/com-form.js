'use strict';

angular.module('comForm', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('comNew', {
                parent: 'app',
                url: '/companies/new',
                template: '<com-form></com-form>'
            })
            .state('comEdit', {
                parent: 'app',
                url: '/companies/:comId/edit',
                template: '<com-form></com-form>'
            })
    }]);
