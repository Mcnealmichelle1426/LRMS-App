/**
 * Created by kelly on 10/02/2015.
 */
(function(){
    'use strict';
    angular.module('VetechLawApp').controller('MenuController',MenuController);

    MenuController.$inject = ['$scope','$window','NavigationMenuService'];

    function MenuController($scope, $window,navigationMenu){

        var me = this;
        console.log('log');

        me.loadMenu = loadMenu;
        me.Login = null;
        me.LoginModel = {};

        me.menuData = [
            {
                name:"dashboard",
                isRoot:false,
                icon:"fa fa-dashboard",
                href:'index.html#/dash-board'
            },{
                name:"system Config",
                isRoot:true,
                icon:"fa fa-desktop",
                items:[
                    {
                        name:"case classification",
                        href:"index.html#/case-classification",
                        isRoot:false
                    },
                    {
                        name:"instruction configuration",
                        href:"index.html#/instruction-configuration",
                        isRoot:false
                    },
                    {
                        name:"identification type",
                        href:"index.html#/identification-type",
                        isRoot:false
                    },
                    {
                        name:"client type",
                        href:"index.html#/client-type",
                        isRoot:false
                    },
                    {
                        name:"Job title",
                        href:"index.html#/job-title",
                        isRoot:false
                    },
                    {
                        name:"branches",
                        href:"index.html#/branches",
                        isRoot:false
                    },
                    {
                        name:"roles",
                        href:"index.html#/roles",
                        isRoot:false
                    },
                    {
                        name:"role group",
                        href:"index.html#/role-group",
                        isRoot:false
                    }
                ]
            },{
                name:"Clients",
                isRoot:true,
                icon:"fa fa-cogs",
                items:[
                    {
                        name:"Client registration",
                        href:"index.html#/client-registration",
                        isRoot:false
                    },
                    {
                        name:"Payments",
                        href:"index.html#/payments",
                        isRoot:false
                    }
                ]
            },{
                name:"Files",
                isRoot:false,
                icon:"fa fa-cogs"
            },{
                name:"Accounts",
                isRoot:false,
                icon:"fa fa-cogs"
            },{
                name:"Settings",
                isRoot:false,
                icon:"fa fa-cogs"
            }];

        $scope.treedata =
            [
                { "label" : "dashboard", "id" : "index.html#/dash-board", "children" : [] },
                { "label" : "system Configuration", "id" : "role1", "children" : [
                    { "label" : "case classification", "id" : "index.html#/case-classification", "children" : [] },
                    { "label" : "instruction configuration", "id" : "index.html#/instruction-configuration", "children" : []},
                    { "label" : "identification type", "id" : "index.html#/identification-type", "children" : []},
                    { "label" : "client type", "id" : "index.html#/identification-type", "children" : []},
                    { "label" : "Job title", "id" : "index.html#/job-title", "children" : []},
                    { "label" : "branches", "id" : "index.html#/branches", "children" : []},
                    { "label" : "roles", "id" : "index.html#/roles", "children" : []},
                    { "label" : "role group", "id" : "index.html#/role-group", "children" : []}
                ]},
                { "label" : "Client", "id" : "role2", "children" : [
                    { "label" : "Client registration", "id" : "index.html#/client-registration", "children" : [] },
                    { "label" : "payments", "id" : "index.html#/payments", "children" : [] }
                ] },
                { "label" : "Files", "id" : "role3", "children" : [] },
                { "label" : "Accounts", "id" : "role3", "children" : [] },
                { "label" : "Settings", "id" : "role3", "children" : [] }
            ];
        $scope.$watch( 'abc.currentNode', function( newObj, oldObj ) {
            if( $scope.abc && angular.isObject($scope.abc.currentNode) ) {
                console.log( 'Node Selected!!' );
                console.log( $scope.abc.currentNode );
                $window.location = $scope.abc.currentNode.id;
            }
        }, false);

        function loadMenu(){
            var authData = me.Login;
            /**
             * if the user login is successful load the MainTemplate
             */
            navigationMenu.getNavigationMenu(authData).then(function(data){
                if(data.success){
                    $window.location = 'index.html';
                }else{
                    console.log('wrong username or password');
                }
            },function(error){
                console.log(error);
            });
        }
    }
})();
