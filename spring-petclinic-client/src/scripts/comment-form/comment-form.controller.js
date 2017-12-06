'use strict';

angular.module('commentForm')
    .controller('CommentFormController', ["$http", '$state', '$stateParams', function ($http, $state, $stateParams) {
        var self = this;

        var comId = $stateParams.comId || 0;

        if (!comId) {
            self.companies = {};
        } else {
            $http.get("companies/" + comId).then(function (resp) {
                self.companies = resp.data;
            });
        }

        self.submitCompaniesForm = function () {
            var id = self.companies.id;

            if (id) {
                $http.put('companies/' + id, self.companies).then(function () {
                    $state.go('comDetails', {comId: comId});
                });
            } else {
                $http.post('companies', self.companies).then(function () {
                    $state.go('companies');
                });
            }
        };
    }]);
