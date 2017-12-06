'use strict';

angular.module('commentForm', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('comNew', {
                parent: 'app',
                url: '/companies/new',
                template: '<comment-form></comment-form>'
            })
            .state('comEdit', {
                parent: 'app',
                url: '/companies/:comId/edit',
                template: '<comment-form></comment-form>'
            })
    }]);
