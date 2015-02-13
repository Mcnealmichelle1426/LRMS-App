/**
 * Created by kelly on 10/02/2015.
 */
(function(){
    'use strict';
    angular.module('VetechLawApp').controller('loginController',loginController);

    loginController.$inject = ['$window','LoginService','$rootScope'];

    function loginController($window,LoginService){
        var me = this;

        me.authenticateUser = authenticateUser;
        me.Login = null;
        me.isSaving = null;
        me.LoginModel = {};
        me.userData = null;
        me.loggedIn = loggedIn;

        function loggedIn(){
            if($window.sessionStorage.getItem("authentication")){
                return JSON.parse($window.sessionStorage.getItem("authentication"));
            }else{
                // $window.location = 'login.html'
            }
        }
        function authenticateUser(){
            var authData = me.Login;
            /**
             * if the user login is successful load the MainTemplate
             */
            me.isSaving = true;
            LoginService.LoginUser(authData).then(function(data){
                me.isSaving = false;
                clearAuthData()
                if(data.success){
                    $window.location = 'index.html';
                    /**
                     * clear local storage and user data
                     */
                    setUserData(data)

                }else{
                    console.log('wrong username or password');
                }
            });
        }
        function clearAuthData() {
            $window.sessionStorage.removeItem("authentication");
        }
        function setUserData(data){
            $window.sessionStorage.setItem("authentication", JSON.stringify(data));
        }
    }
})();
