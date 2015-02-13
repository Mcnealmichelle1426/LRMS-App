/**
 * Created by kelly on 10/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('JobTitleController',JobTitleController);

    JobTitleController.$inject = ['lodash','JobTitleService'];

    function JobTitleController(_,JobTitleService){
        var me = this;
        me.jobTitle = {};
        me.getAllJobTitleController = getAllJobTitleController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.jobTitles = [];
        me.deactivateItem = deactivateItem;

        me.jobTitles = [
            {
                id:1,
                active:true,
                roleGroup:'roleGroup 1',
                name:"name 1",
                description:'description',
                basicSalary: '2000'
            },{
                id:2,
                active:false,
                roleGroup:'roleGroup 2',
                name:"name 2",
                description:'description',
                basicSalary: '2000'
            },{
                id:3,
                active:false,
                roleGroup:'roleGroup 3',
                name:"name 3",
                description:'description',
                basicSalary: '2000'
            },{
                id:4,
                active:true,
                roleGroup:'roleGroup 4',
                name:"name 4",
                description:'description',
                basicSalary: '2000'
            },{
                id:5,
                active:true,
                roleGroup:'roleGroup 5',
                name:"name 5",
                description:'description',
                basicSalary: '2000'
            }
        ];

        getAllJobTitleController();

        function getAllJobTitleController(){
            console.log(_.isArray(me.jobTitles));
            //JobTitleService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.jobTitle);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.jobTitle.id){
                JobTitleService.editJobTitle(me.jobTitle, me.jobTitle.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.jobTitles,{id:me.jobTitle.id});
                    if(index > -1){
                        _.extend(me.jobTitles[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.jobTitles.push(me.jobTitle);
                JobTitleService.saveJobTitle(me.jobTitle).then(function(data){
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
            me.jobTitle =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.jobTitles,{id:data.id});
            if(index > -1){
                me.jobTitles.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.jobTitle = {};
        }
    }
})();


