'use strict';

angular.module('commentDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('commentDetails', {
                parent: 'app',
                url: '/companies/details/:comId',
                template: '<comment-details></comment-details>'
            }).state('addComment', {
                parent: 'app',
                url: '/companies/:comId/comments',
                template: '<comment-details></comment-details>'
            })
    }]);