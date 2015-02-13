/**
 * Created by kelly on 10/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('BranchService',BranchServiceProvider);

    function BranchServiceProvider(){

        var me = this;
        me.BranchBaseUrl = 'rest/Branch/';
        me.$get = BranchService;

        BranchService.$inject = ["$http", "$q"];

        function BranchService($http, $q){
            return {
                getBranch : getBranch,
                saveBranch: saveBranch,
                editBranch: editBranch
            };
            function getBranch(){
                var url = me.BranchBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveBranch(data){
                var url = me.BranchBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editBranch(data,id){
                var url = me.BranchBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();


