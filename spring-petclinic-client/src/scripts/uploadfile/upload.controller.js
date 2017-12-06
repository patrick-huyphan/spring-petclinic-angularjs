'use strict';
angular.module('upload',[]).constant('urls', {
    DOC_URL: 'http://localhost:8080/doc/'
}).factory('docService', ['$http', '$q', 'urls', function ($http, $q, urls) {

            var factory = {
                saveDoc: saveDoc,
                findDoc: findDoc
            };

            return factory;

            function saveDoc(file) {
                var deferred = $q.defer();
                var formData = new FormData();
                formData.append('file', file);

                $http.post(urls.DOC_URL+'upload', formData,{
                    transformRequest : angular.identity,
                    headers : {
                        'Content-Type' : undefined
                    }})
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            alert(errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            };

            function findDoc(docId) {
                var deferred = $q.defer();
                $http.get(urls.DOC_URL + '/'+docId)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            alert(errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]).controller('UploadController',
    ['$scope', '$rootScope','docService','$http', function($scope, $rootScope, docService, $http) {

        $scope.file = '';

        $scope.upload = function(){
            var file = $scope.file;
            docService.saveDoc(file)
                .then(
                    function (response) {
                        alert("file uploaded successfully.");
                        $http.get("http://localhost:8080/doc/").success(
                            function(response) {
                                $rootScope.docList = response;
                            });
                    },
                    function (errResponse) {

                    }
                );
        }
    }
    ]).directive('fileModel', [ '$parse', function($parse) {
    return {
        restrict : 'A',
        link : function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
} ]).run(function($rootScope, $http) {
    $http.get("http://localhost:8080/doc/").success(
        function(response) {
            $rootScope.docList = response;
        });
});