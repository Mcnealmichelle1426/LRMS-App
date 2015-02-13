/**
 * Created by kelly on 10/02/2015.
 */
(function(){
    'use strict';
    angular
        .module('VetechLawApp')
        .provider('NavigationMenuService',NavigationMenuServiceProvider);

    function NavigationMenuServiceProvider(){

        var me = this;
        me.NavigationMenuBaseUrl = 'rest/Menu.php';
        me.$get = NavigationMenuService;

        NavigationMenuService.$inject = ["$http", "$q"];

        function NavigationMenuService($http, $q){
            return {
                getNavigationMenu : getNavigationMenu
            };
            function getNavigationMenu(data){
                var url = me.NavigationMenuBaseUrl;
                /**
                 * get the navigation menu based on the logged in user
                 */
                return $http.get(url, data).then(function (result) {
                    return result.data;
                });
            }
        }
    }
})();
