/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('JobTitleService',JobTitleServiceProvider);

    function JobTitleServiceProvider(){

        var me = this;
        me.JobTitleBaseUrl = 'rest/JobTitle/';
        me.$get = JobTitleService;

        JobTitleService.$inject = ["$http", "$q"];

        function JobTitleService($http, $q){
            return {
                getJobTitle : getJobTitle,
                saveJobTitle: saveJobTitle,
                editJobTitle: editJobTitle
            };
            function getJobTitle(){
                var url = me.JobTitleBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveJobTitle(data){
                var url = me.JobTitleBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editJobTitle(data,id){
                var url = me.JobTitleBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
