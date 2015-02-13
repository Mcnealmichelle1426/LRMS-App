/**
 * Created by kelly on 11/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('RoleGroupService',RoleGroupServiceProvider);

    function RoleGroupServiceProvider(){

        var me = this;
        me.RoleGroupBaseUrl = 'rest/RoleGroup/';
        me.$get = RoleGroupService;

        RoleGroupService.$inject = ["$http", "$q"];

        function RoleGroupService($http, $q){
            return {
                getRoleGroup : getRoleGroup,
                saveRoleGroup: saveRoleGroup,
                editRoleGroup: editRoleGroup
            };
            function getRoleGroup(){
                var url = me.RoleGroupBaseUrl;
                return $http.get(url).then(function (result) {
                    return result.data;
                });
            }
            function saveRoleGroup(data){
                var url = me.RoleGroupBaseUrl;
                return $http.post(url,data).then(function (result) {
                    return result.data;
                });
            }
            function editRoleGroup(data,id){
                var url = me.RoleGroupBaseUrl+id;
                return $http.put(url,data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
