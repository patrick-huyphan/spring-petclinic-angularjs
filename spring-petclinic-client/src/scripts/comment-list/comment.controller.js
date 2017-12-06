'use strict';

angular.module('comments')
    .controller('CommentsController', ['$http', '$state', '$stateParams', '$filter', function ($http, $state, $stateParams, $filter) {
        var self = this;
        var petId = $stateParams.Id || 0;
        var url = "/companies/" + petId + "/comments";
        self.date = new Date();
        self.desc = "";

        $http.get(url).then(function (resp) {
            self.comments = resp.data;
        });

        self.submit = function () {
            var data = {
                date: $filter('date')(self.date, "yyyy-MM-dd"),
                description: self.desc
            };

            $http.post(url, data).then(function () {
                $state.go('comDetails', { Id: $stateParams.Id });
            });
        };
    }]);
