/**
 * Created by kelly on 10/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('LoginService',LoginServiceProvider);

    function LoginServiceProvider(){

        var me = this;
        me.LoginBaseUrl = 'rest/login.php';
        me.$get = LoginService;

        LoginService.$inject = ["$http", "$q"];

        function LoginService($http, $q){
            return {
                LoginUser : LoginUser
            };
            function LoginUser(data){
                var url = me.LoginBaseUrl;
                return $http.post(url, data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
