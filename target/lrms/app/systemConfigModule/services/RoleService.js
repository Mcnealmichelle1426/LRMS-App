/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('RoleService',RoleServiceProvider);

    function RoleServiceProvider(){

        var me = this;
        me.RoleBaseUrl = 'rest/role/';
        me.$get = RoleService;

        RoleService.$inject = ["$http", "$q"];

        function RoleService($http, $q){
            return {
                getRole : getRole,
                saveRole: saveRole,
                editRole: editRole,
                deleteRole:deleteRole
            };
            function getRole(){
                var url = me.RoleBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveRole(data){
                var url = me.RoleBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editRole(data,id){
                var url = me.RoleBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
            function deleteRole(id){
                var url = me.RoleBaseUrl+id;
                return $http.delete(url).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
