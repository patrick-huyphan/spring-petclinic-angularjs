'use strict';

angular.module('upload', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('upload', {
                parent: 'app',
                url: '/upload',
                template: '<upload></upload>'
            })
    }]);