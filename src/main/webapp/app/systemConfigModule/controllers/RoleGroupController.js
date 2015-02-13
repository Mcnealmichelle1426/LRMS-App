/**
 * Created by kelly on 11/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('RoleGroupController',RoleGroupController);

    RoleGroupController.$inject = ['lodash','RoleGroupService'];

    function RoleGroupController(_,RoleGroupService){
        var me = this;
        me.roleGroup = {};
        me.getAllRoleGroupController = getAllRoleGroupController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.roleGroups = [];
        me.deactivateItem = deactivateItem;

        me.roleGroups = [
            {
                id:1,
                active:true,
                roleGroup:'roleGroup 1',
                name:"name 1",
                description:'description'
            },{
                id:2,
                active:false,
                roleGroup:'roleGroup 2',
                name:"name 2",
                description:'description'
            },{
                id:3,
                active:false,
                roleGroup:'roleGroup 3',
                name:"name 3",
                description:'description'
            },{
                id:4,
                active:true,
                roleGroup:'roleGroup 4',
                name:"name 4",
                description:'description'
            },{
                id:5,
                active:true,
                roleGroup:'roleGroup 5',
                name:"name 5",
                description:'description'
            }
        ];

        getAllRoleGroupController();

        function getAllRoleGroupController(){
            console.log(_.isArray(me.roleGroups));
            //RoleGroupService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.RoleGroup);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.roleGroup.id){
                RoleGroupService.editRoleGroup(me.roleGroup, me.roleGroup.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.roleGroups,{id:me.roleGroup.id});
                    if(index > -1){
                        _.extend(me.roleGroups[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.roleGroups.push(me.roleGroup);
                RoleGroupService.saveRoleGroup(me.roleGroup).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }
        }
        function editData(data){
            me.roleGroup =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.roleGroups,{id:data.id});
            if(index > -1){
                me.roleGroups.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.roleGroup = {};
        }
    }
})();



