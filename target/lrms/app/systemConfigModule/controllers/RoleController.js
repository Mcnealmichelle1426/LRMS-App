/**
 * Created by kelly on 11/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('RoleController',RoleController);

    RoleController.$inject = ['lodash','RoleService'];

    function RoleController(_,RoleService){
        var me = this;
        me.role = {};
        me.getAllRoleController = getAllRoleController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.roles = [];
        me.deactivateItem = deactivateItem;


        getAllRoleController();

        function getAllRoleController(){
            console.log(_.isArray(me.roles));
            RoleService.getRole().then(function(data){
                console.log(data);
                me.roles = data;
            });

        }
        function saveChanges(){
            console.log(me.role.roleID);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.role.roleID){
                RoleService.editRole(me.role, me.role.roleID).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.roles,{roleID:me.role.roleID});
                    console.log(index);
                    console.log(data);
                    if(index > -1){
                        _.extend(me.roles[index],data);
                        me.role = null;
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.roles.push(me.role);
                RoleService.saveRole(me.role).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    me.role = null;
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }
        }
        function editData(data){
            me.role =  data;
        }

        function deactivateItem(data){
            RoleService.deleteRole(data.roleID).then(function(data){
                console.log(data);
            })
            var index  = _.findIndex(me.roles,{roleID:data.roleID});
            if(index > -1){
                me.roles.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            me.role = {};
        }
    }
})();



